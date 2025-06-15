package com.example.shipmentservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExecutionPlanDto extends AuditableDto<Long> {

    private Long id;
    private String priority;
    private LocalDate estimatedDeliveryDate;
    private Boolean fragile ;
    private Boolean notifyCustomer ;
    private Long planTemplateId;

    private String shipmentId;
    private PlanTemplateDto planTemplate;
    private ShipmentDto shipment;
}
