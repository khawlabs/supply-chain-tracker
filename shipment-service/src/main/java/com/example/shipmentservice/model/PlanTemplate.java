package com.example.shipmentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PlanTemplate extends Auditable<Long>  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} , fetch = FetchType.EAGER)
    @JoinTable(
            name = "plan_template_actions",
            joinColumns = @JoinColumn(name = "plan_template_id"),
            inverseJoinColumns = @JoinColumn(name = "action_id")
    )
    private List<Action> actions = new ArrayList<>();

    @Version
    private Long version; // Enables optimistic locking for internal communication for db integration

}
