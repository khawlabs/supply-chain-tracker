package com.example.shipmentservice.mapper;
import com.example.shipmentservice.dto.ShipmentDto;
import com.example.shipmentservice.model.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Mapper( nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring" )
public interface ShipmentMapper extends EntityMapper<Shipment, ShipmentDto> {

}

