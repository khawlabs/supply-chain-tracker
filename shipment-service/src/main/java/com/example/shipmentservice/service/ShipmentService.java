package com.example.shipmentservice.service;


import com.example.shipmentservice.dto.ShipmentDto;
import com.example.shipmentservice.exception.RessourceNotFoundException;
import com.example.shipmentservice.kafka.ShipmentEventProducer;
import com.example.shipmentservice.mapper.ShipmentMapper;
import com.example.shipmentservice.model.Shipment;
import com.example.shipmentservice.repository.ShipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;

    private final ShipmentMapper shipmentMapper;
    private final ShipmentEventProducer eventProducer;
    private static final String SHIPMENT_NOT_FOUND = "Shipment not found";
    @Autowired
    private NotificationService notificationService;

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
            Shipment shipment = shipmentMapper.dtoToEntity( shipmentDto );
            shipment.setStatus("CREATED");
            Shipment shipmentSaved = shipmentRepository.save(shipment);

            if (sendNotification) {
                eventProducer.sendNotification(shipmentSaved);
                notificationService.notifyShipmentCreated(shipmentSaved);
            }
            log.info("✅ Shipment created: {}", shipmentDto.getShipmentId());
            return shipmentSaved;

        } catch (Exception e) {
            log.error("❌ Failed to create shipment", e);
            throw new Exception("Failed to create shipment", e);
        }
    }
    public Shipment updateShipment( Long id, ShipmentDto shipmentDto ) {

        Optional<Shipment> shipmentSearched = shipmentRepository.findById( id );
        Shipment shipmentToUpdate = shipmentMapper.dtoToEntity(shipmentDto) ;
        if ( shipmentSearched.isPresent() ) {
            shipmentToUpdate = shipmentRepository.save( shipmentToUpdate );
            return shipmentToUpdate;
        } else
            throw new RessourceNotFoundException( SHIPMENT_NOT_FOUND );
    }

    public List<Shipment> getAllShipments() {
        List<Shipment> shipments = shipmentRepository.findAll();
        return shipments;
    }

    public Shipment getShipmentById( Long id ) {
        Optional<Shipment> shipmentSearch = shipmentRepository.findById( id );
        if ( shipmentSearch.isPresent() ) {
            return shipmentSearch.get();
        } else {
            throw new RessourceNotFoundException( "shipment with id " + id + " not found" );
        }
    }

    public Shipment getShipmentByShipmentId( String shipmentId ) {
        Optional<Shipment> shipmentSearch = shipmentRepository.findByShipmentId( shipmentId );
        if ( shipmentSearch.isPresent() ) {
            return shipmentSearch.get();
        } else {
            throw new RessourceNotFoundException( "shipment with id " + shipmentId + " not found" );
        }
    }
    @Transactional
    public void deleteShipment(Long id) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shipment with ID " + id + " not found"));
        shipmentRepository.delete(shipment);
        log.info("🗑️ Shipment deleted: {}", id);
    }

    public List<Shipment> getUnassignedShipments() {
        return shipmentRepository.findUnassignedShipments();
    }
    public Boolean checkAssignedShipment(Long id) {
        return shipmentRepository.isShipmentAssigned(id);
    }

}
