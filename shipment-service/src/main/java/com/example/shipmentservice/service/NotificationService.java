package com.example.shipmentservice.service;

import com.example.shipmentservice.dto.ShipmentNotification;
import com.example.shipmentservice.model.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void notifyShipmentCreated(Shipment shipment) {
        ShipmentNotification notification = new ShipmentNotification(
                shipment.getId(),
                "📦 New shipment created: " + shipment.getShipmentId()
        );
        messagingTemplate.convertAndSend("/topic/shipments", notification);
    }
}