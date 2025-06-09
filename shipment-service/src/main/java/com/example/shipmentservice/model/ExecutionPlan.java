package com.example.shipmentservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecutionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shipmentId;
    private String origin;
    private String destination;
    private String priority;
    private LocalDate estimatedDeliveryDate;

    private String status; // e.g. "CREATED"
}