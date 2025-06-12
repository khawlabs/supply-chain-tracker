package com.example.shipmentservice.mapper;

import com.example.shipmentservice.dto.PlanTemplateDto;
import com.example.shipmentservice.model.PlanTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper( nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring" )
public interface PlanTemplateMapper extends EntityMapper<PlanTemplate, PlanTemplateDto> {

}
