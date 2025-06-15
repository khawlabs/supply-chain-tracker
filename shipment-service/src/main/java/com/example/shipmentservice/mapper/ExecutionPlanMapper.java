package com.example.shipmentservice.mapper;

import com.example.shipmentservice.dto.ExecutionPlanDto;
import com.example.shipmentservice.model.ExecutionPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper( nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring" )
public interface ExecutionPlanMapper extends EntityMapper<ExecutionPlan, ExecutionPlanDto> {
    @Mapping(source = "planTemplate.id", target = "planTemplateId")
    @Mapping(source = "shipment.shipmentId", target = "shipmentId")
    @Mapping(source = "planTemplate", target = "planTemplate")
    @Mapping(source = "shipment", target = "shipment")
    ExecutionPlanDto entityToDto(ExecutionPlan entity);

}