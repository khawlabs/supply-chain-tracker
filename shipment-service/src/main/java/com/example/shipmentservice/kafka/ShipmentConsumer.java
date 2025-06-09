package com.example.shipmentservice.kafka;

import com.example.shipmentservice.config.KafkaTopicConfig;
import com.example.shipmentservice.dto.ShipmentDto;
import com.example.shipmentservice.model.ExecutionPlan;
import com.example.shipmentservice.model.Shipment;
import com.example.shipmentservice.repository.ExecutionPlanRepository;
import com.example.shipmentservice.repository.ShipmentRepository;
import com.example.shipmentservice.service.ShipmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShipmentConsumer {

    private final ShipmentService shipmentService;
    private final ObjectMapper objectMapper; // ✅ Auto-configured by Spring Boot

    @KafkaListener(topics = "${topic.shipment.created}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeShipment(String message) {
        try {
            ShipmentDto dto = objectMapper.readValue(message, ShipmentDto.class);
            shipmentService.createShipment(dto, true);
        } catch (Exception e) {
            log.error("❌ Failed to process shipment message", e);
        }
    }
}
