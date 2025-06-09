package com.example.shipmentservice.service;


import com.example.shipmentservice.dto.ShipmentDto;
import com.example.shipmentservice.kafka.ShipmentEventProducer;
import com.example.shipmentservice.model.Shipment;
import com.example.shipmentservice.repository.ShipmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentEventProducer eventProducer;

    public Shipment createShipment(ShipmentDto shipmentDto, boolean sendNotification) throws Exception {
        try {
            log.info("✅ Shipment received: {}", shipmentDto.getShipmentId());
            Shipment shipment = Shipment.builder()
                    .id(shipmentDto.getId())
                    .shipmentId(shipmentDto.getShipmentId())
                    .origin(shipmentDto.getOrigin())
                    .destination(shipmentDto.getDestination())
                    .createdAt(shipmentDto.getCreatedAt())
                    .status("CREATED")
                    .build();
            Shipment shipmentSaved = shipmentRepository.save(shipment);

            if (sendNotification) {
                eventProducer.sendNotification(shipmentSaved);
            }
            log.info("✅ Shipment created: {}", shipmentDto.getShipmentId());
            return shipmentSaved;
        } catch (Exception e) {
            throw new Exception("Failed to create shipment", e);
        }
    }

    public List<Shipment> getAllShipments() {
        List<Shipment> shipments = shipmentRepository.findAll();
        return shipments;
    }

}
