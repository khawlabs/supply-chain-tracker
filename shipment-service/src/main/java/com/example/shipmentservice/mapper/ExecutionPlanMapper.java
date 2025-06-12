package com.example.shipmentservice.mapper;

import com.example.shipmentservice.dto.ExecutionPlanDto;
import com.example.shipmentservice.model.ExecutionPlan;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper( nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring" )
public interface ExecutionPlanMapper extends EntityMapper<ExecutionPlan, ExecutionPlanDto> {

}