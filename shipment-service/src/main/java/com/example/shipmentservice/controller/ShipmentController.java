package com.example.shipmentservice.controller;
import com.example.shipmentservice.dto.ShipmentDto;
import com.example.shipmentservice.mapper.ShipmentMapper;
import com.example.shipmentservice.model.Shipment;
import com.example.shipmentservice.service.ShipmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShipmentController {
    private final ShipmentService shipmentService;
    private final ShipmentMapper shipmentMapper;

    @PostMapping("/shipments")
    public ResponseEntity<?> createShipment( @RequestBody ShipmentDto shipmentDto ) {
        if ( shipmentDto == null ) {
            return new ResponseEntity<>( "", HttpStatus.BAD_REQUEST );
        }
        try {
            Shipment shipmentCreated = shipmentService.createShipment( shipmentDto, true );
            ShipmentDto shipment= shipmentMapper.entityToDto( shipmentCreated );
            return new ResponseEntity<>( shipment, HttpStatus.CREATED );
        }
        catch ( Exception e ) {
            log.error( "Exception when calling createDevice {}", e );
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
    @PutMapping("/shipments")
    public ResponseEntity<?> updateShipment( Long id, ShipmentDto shipmentDto ) {
        if ( shipmentDto == null || id == null ) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Shipment shipmentUpdated = shipmentService.updateShipment( id, shipmentDto );
            ShipmentDto Shipment = shipmentMapper.entityToDto( shipmentUpdated );
            return new ResponseEntity<>( Shipment, HttpStatus.ACCEPTED );

        } catch ( Exception e ) {
            log.error( "Exception when calling updateShipment {}", e );
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
    @GetMapping("/shipments")
    public ResponseEntity<?> getAllShipments() {
        try {
            List<Shipment> shipments = shipmentService.getAllShipments();

            if ( CollectionUtils.isEmpty( shipments ) ) {
                return ResponseEntity.noContent().build();
            } else {
                List<ShipmentDto> shipmentsDto = shipmentMapper.listEntityToDto( shipments );
                return ResponseEntity.ok( shipmentsDto );
            }
        }
        catch ( Exception e ) {
            log.error( "Exception when calling getDevices {}", e );
            return new ResponseEntity<>( e, HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
    @GetMapping("/shipments/{id}")
    public ResponseEntity<?> getShipmentById( @PathVariable Long id ) {
        if ( id == null ) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
        try {
            Shipment shipment = shipmentService.getShipmentById(id);
            ShipmentDto dto = shipmentMapper.entityToDto(shipment);
            return new ResponseEntity<>( dto, HttpStatus.OK );
        }
        catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch ( Exception e ) {
            log.error( "Exception when calling getDeviceById {}", e );
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        try {
            shipmentService.deleteShipment(id);
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // HTTP 404
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // HTTP 500
        }
    }
}