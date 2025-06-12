package com.example.shipmentservice.service;

import com.example.shipmentservice.dto.PlanTemplateDto;
import com.example.shipmentservice.dto.ShipmentDto;
import com.example.shipmentservice.exception.RessourceNotFoundException;
import com.example.shipmentservice.mapper.PlanTemplateMapper;
import com.example.shipmentservice.model.PlanTemplate;
import com.example.shipmentservice.model.Shipment;
import com.example.shipmentservice.repository.PlanTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanTemplateService {

    private final PlanTemplateRepository planTemplateRepository;
    private final PlanTemplateMapper planTemplateMapper;
    private static final String PLAN_NOT_FOUND = "PlanTemplate not found";
    public List<PlanTemplate> getAllPlanTemplates() {
        return planTemplateRepository.findAll();
    }

    public PlanTemplate getByIdPlanTemplate(Long id) {
        return planTemplateRepository.findById(id).get();
    }

    public PlanTemplate createPlanTemplate(PlanTemplate template) {
        return planTemplateRepository.save(template);
    }
    public PlanTemplate updatePlanTemplate(Long id, PlanTemplateDto planTemplateDto ) {

        Optional<PlanTemplate> planSearched = planTemplateRepository.findById( id );
        if ( planSearched.isPresent() ) {
            PlanTemplate planTemplateToUpdate = planTemplateMapper.dtoToEntity(planTemplateDto) ;
            planTemplateToUpdate = planTemplateRepository.save( planTemplateToUpdate );
            return planTemplateToUpdate;
        } else
            throw new RessourceNotFoundException( PLAN_NOT_FOUND );
    }
    public void deletePlanTemplate(Long id) {
        planTemplateRepository.deleteById(id);
    }
}
