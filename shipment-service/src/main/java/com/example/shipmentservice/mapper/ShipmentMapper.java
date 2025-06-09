package com.example.shipmentservice.mapper;
import com.example.shipmentservice.dto.ShipmentDto;
import com.example.shipmentservice.model.Shipment;
public class ShipmentMapper {

    public static Shipment mapToEntity(ShipmentDto dto) {
        if (dto == null) return null;

        return Shipment.builder()
                .id(dto.getId())
                .shipmentId(dto.getShipmentId())
                .origin(dto.getOrigin())
                .destination(dto.getDestination())
                .status(dto.getStatus())
                .createdAt(dto.getCreatedAt())
                .build();
    }

    public static ShipmentDto mapToDto(Shipment entity) {
        if (entity == null) return null;

        return ShipmentDto.builder()
                .id(entity.getId())
                .shipmentId(entity.getShipmentId())
                .origin(entity.getOrigin())
                .destination(entity.getDestination())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}

