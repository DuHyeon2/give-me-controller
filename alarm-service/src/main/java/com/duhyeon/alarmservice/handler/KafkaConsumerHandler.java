package com.duhyeon.alarmservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumerHandler {

    private final WebsocketHandler websocketHandler;

    public KafkaConsumerHandler(WebsocketHandler websocketHandler) {
        this.websocketHandler = websocketHandler;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> record) {
        String key = record.key();
        String value = record.value();
        log.info("Kafka message received. key: {}, value: {}", key, value);

        try {
            websocketHandler.noticeSend(key, value);
        } catch (Exception e) {
            log.error("Error sending message to WebSocket sessions: {}", e.getMessage());
        }
    }
}
