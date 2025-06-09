package com.example.shipmentservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String shipmentId;

    @Version
    private Long version; // Enables optimistic locking for internal communication for db integration

    private String origin;
    private String destination;
    private String status;
    private LocalDateTime createdAt;
}
