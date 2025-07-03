package com.duhyeon.tempservice.handler;

import com.duhyeon.tempservice.dto.AirConditionResponseDTO;
import com.duhyeon.tempservice.service.TempService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumerHandler {

    private final TempService tempService;

    public KafkaConsumerHandler(TempService tempService) {
        this.tempService = tempService;
    }


    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> record) {
        String key = record.key();
        String value = record.value();
        log.info("Kafka message received. key: {}, value: {}", key, value);

        try {
            AirConditionResponseDTO currentCondition = tempService.getAirCondition();
            tempService.updateAirCondition(currentCondition);
        } catch (Exception e) {
            log.error("Error sending message to WebSocket sessions: {}", e.getMessage());
        }
    }
}
