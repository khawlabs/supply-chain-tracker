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
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentEventProducer eventProducer;
    @Transactional
    public Shipment createShipment(ShipmentDto shipmentDto, boolean sendNotification) throws Exception {
        try {
            log.info("✅ Shipment received: {}", shipmentDto.getShipmentId());

            // Check if shipment already exists by unique shipmentId
            Optional<Shipment> existing = shipmentRepository.findByShipmentId(shipmentDto.getShipmentId());
            if (existing.isPresent()) {
                log.warn("⚠ Shipment with ID {} already exists. Skipping creation.", shipmentDto.getShipmentId());
                return existing.get();
            }

            Shipment shipment = Shipment.builder()
                    .id(shipmentDto.getId()) // Optional: omit this if DB auto-generates it
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
            log.error("❌ Failed to create shipment", e);
            throw new Exception("Failed to create shipment", e);
        }
    }


    public List<Shipment> getAllShipments() {
        List<Shipment> shipments = shipmentRepository.findAll();
        return shipments;
    }

}
