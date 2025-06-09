package com.example.shipmentservice.kafka;

import com.example.shipmentservice.config.KafkaTopicConfig;
import com.example.shipmentservice.model.Shipment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class ShipmentEventProducer {

    private final KafkaTopicConfig kafkaTopicConfig;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public void sendNotification(Shipment shipment) {
        try {
            String message = objectMapper.writeValueAsString(shipment);
            kafkaTemplate.send(kafkaTopicConfig.getShipmentNotification(), message);
            log.info("📣 Notification sent for shipment: {}", shipment.getShipmentId());
        } catch (JsonProcessingException e) {
            log.error("❌ Failed to send notification", e);
        }
    }
}
