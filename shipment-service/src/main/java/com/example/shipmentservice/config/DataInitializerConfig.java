package com.example.shipmentservice.config;

import com.example.shipmentservice.model.Action;
import com.example.shipmentservice.model.PlanTemplate;
import com.example.shipmentservice.repository.ActionRepository;
import com.example.shipmentservice.repository.PlanTemplateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializerConfig {

    @Bean
    public CommandLineRunner dataInitializer(
            PlanTemplateRepository templateRepository,
            ActionRepository actionRepository
    ) {
        return args -> {
            List<String> actionNames = List.of(
                    "shipment is taken from customer",
                    "shipment is on the way",
                    "shipment is arrived to destination",
                    "shipment is handover to the destination target"
            );

            List<Action> savedActions = new ArrayList<>();
            for (String name : actionNames) {
                Action action = actionRepository.findByName(name)
                        .orElseGet(() -> actionRepository.save(new Action(null, name, null)));
                savedActions.add(action);
            }

            if (templateRepository.findByName("General Shipment Template").isEmpty()) {
                PlanTemplate template = new PlanTemplate();
                template.setName("General Shipment Template");
                template.setActions(savedActions);
                templateRepository.save(template);
            }
        };
    }
}
