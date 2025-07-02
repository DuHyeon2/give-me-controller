package com.duhyeon.controlservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
public class KafkaProducerHandler {

    @Value("${spring.kafka.topic.name}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerHandler(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendKafkaProducerMessage(String key, String message) {
        try {
            kafkaTemplate.send(topic, key, message);
            log.info("Message sent to Kafka topic {}: {}", topic, message);
        } catch (Exception e) {
            log.error("Failed to send message to Kafka topic {}: {}", topic, e.getMessage());
        }
    }
}
