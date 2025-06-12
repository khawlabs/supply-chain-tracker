package com.example.shipmentservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Shipment extends Auditable<Long>  {

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

 /*   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "execution_plan_id", referencedColumnName = "id")
    private ExecutionPlan executionPlan;*/
}
