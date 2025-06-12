package com.example.shipmentservice.model;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ExecutionPlan extends Auditable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String priority;
    private LocalDate estimatedDeliveryDate;
    private Boolean fragile ;
    private Boolean notifyCustomer ;

    private String status; // e.g. "CREATED"

    @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    //@JsonIgnore // prevents this field from being included in JSON responses or requests.Useful to avoid infinite loops or unnecessary data during serialization.
    @JoinColumn(name = "plan_template_id")
    private PlanTemplate planTemplate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "shipment_id", referencedColumnName = "id")
    private Shipment shipment;
}