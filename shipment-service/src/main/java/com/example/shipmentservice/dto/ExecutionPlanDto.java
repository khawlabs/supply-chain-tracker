package com.example.shipmentservice.dto;

import com.example.shipmentservice.model.PlanTemplate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    private PlanTemplate planTemplate;
}
