package com.example.shipmentservice.controller;
import com.example.shipmentservice.dto.ShipmentDto;
import com.example.shipmentservice.model.Shipment;
import com.example.shipmentservice.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping("/shipments")
    public ResponseEntity<Shipment> createShipment(@RequestBody ShipmentDto shipmentDto) {
        try {
            Shipment shipment = shipmentService.createShipment(shipmentDto,true);
            return new ResponseEntity<>(shipment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/shipments")
    public ResponseEntity<List<Shipment>> getAllShipments() {
        try {
            List<Shipment> shipments = shipmentService.getAllShipments();

            if (shipments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // Return the shipments wrapped in a ResponseEntity with HTTP status OK
            return ResponseEntity.ok(shipments);
        } catch (Exception e) {
            e.printStackTrace();
            // Return a ResponseEntity with an HTTP status of INTERNAL_SERVER_ERROR
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
