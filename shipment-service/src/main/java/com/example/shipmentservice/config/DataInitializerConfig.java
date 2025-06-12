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

/* @Configuration
public class DataInitializerConfig {

    @Bean
    public CommandLineRunner dataInitializer(
            PlanTemplateRepository templateRepository,
            ActionRepository actionRepository

    ) {
        return args -> {


            List<Action> predefinedActions = List.of(
                    new Action(1L, "shipment is taken from customer",0L),
                    new Action(2L, "shipment is on the way",0L),
                    new Action(3L, "shipment is arrived to destination",0L),
                    new Action(4L, "shipment is handover to the destination target",0L)
            );

            for (Action action : predefinedActions) {
                if (!actionRepository.existsById(action.getId())) {
                    actionRepository.save(action);
                }
            }

            Long templateId = 999L;
            if (!templateRepository.existsById(templateId)) {
                List<Action> actions = actionRepository.findAllById(
                        predefinedActions.stream().map(Action::getId).toList()
                );

                PlanTemplate template = new PlanTemplate();
                template.setId(templateId);
                template.setName("General Shipment Template");
                template.setActions(new ArrayList<>(actions));
                templateRepository.save(template);
            }
            templateRepository.findAll().forEach(System.out::println);
        };
    }
}*/
