package com.example.shipmentservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class KafkaTopicConfig {

    @Value("${topic.shipment.created}")
    private String shipmentCreated;

    @Value("${topic.shipment.notification}")
    private String shipmentNotification;  

    @Value("${spring.kafka.bootstrap-servers}")
    private String shipmentKafkaServer;
}
