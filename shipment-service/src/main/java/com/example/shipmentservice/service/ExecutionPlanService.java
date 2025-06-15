package com.example.shipmentservice.service;

import com.example.shipmentservice.dto.ExecutionPlanDto;
import com.example.shipmentservice.exception.RessourceNotFoundException;
import com.example.shipmentservice.mapper.ExecutionPlanMapper;
import com.example.shipmentservice.model.ExecutionPlan;
import com.example.shipmentservice.model.PlanTemplate;
import com.example.shipmentservice.model.Shipment;
import com.example.shipmentservice.repository.ExecutionPlanRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExecutionPlanService {

    private final ExecutionPlanRepository executionPlanRepository;

    private final ShipmentService shipmentService;
    private final PlanTemplateService planTemplateService;
    private final ExecutionPlanMapper executionPlanMapper;

    private static final String EXECUTION_PLAN_NOT_FOUND = "Execution Plan not found";

    @Transactional
    public ExecutionPlan create(ExecutionPlanDto dto) {
        try {
            // Use services to fetch managed entities
            PlanTemplate planTemplate = planTemplateService.getByIdPlanTemplate(dto.getPlanTemplateId());

            Shipment shipment = shipmentService.getShipmentByShipmentId(dto.getShipmentId());

            // Build execution plan
            ExecutionPlan executionPlan = ExecutionPlan.builder()
                    .priority(dto.getPriority())
                    .estimatedDeliveryDate(dto.getEstimatedDeliveryDate())
                    .fragile(dto.getFragile())
                    .notifyCustomer(dto.getNotifyCustomer())
                    .status("CREATED")
                    .planTemplate(planTemplate)
                    .shipment(shipment)
                    .build();

            // Save the execution plan (cascades to shipment due to @OneToOne + ALL)
            ExecutionPlan saved = executionPlanRepository.save(executionPlan);
            log.info("✅ ExecutionPlan created: {}", saved.getId());
            return saved;

        } catch (Exception e) {
            log.error("❌ Failed to create execution plan", e);
            throw new RuntimeException("Failed to create execution plan", e);
        }
    }
    public ExecutionPlan update( Long id, ExecutionPlanDto executionPlanDto ) {

        Optional<ExecutionPlan> executionPlanSearched = executionPlanRepository.findById( id );
        ExecutionPlan exectionPlanToUpdate = executionPlanMapper.dtoToEntity(executionPlanDto) ;
        if ( executionPlanSearched.isPresent() ) {
            exectionPlanToUpdate = executionPlanRepository.save( exectionPlanToUpdate );
            return exectionPlanToUpdate;
        } else
            throw new RessourceNotFoundException( EXECUTION_PLAN_NOT_FOUND );
    }

    public List<ExecutionPlan> getAllExecutionPlans() {
        List<ExecutionPlan> executionPlans = executionPlanRepository.findAll();
        return executionPlans;
    }

    public ExecutionPlan getExecutionPlanById( Long id ) {
        Optional<ExecutionPlan> executionPlanSearch = executionPlanRepository.findById( id );
        if ( executionPlanSearch.isPresent() ) {
            return executionPlanSearch.get();
        } else {
            throw new RessourceNotFoundException( "executionPlan with id " + id + " not found" );
        }
    }
    @Transactional
    public void deleteExecutionPlan(Long id) {
        ExecutionPlan executionPlan = executionPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ExecutionPlan with ID " + id + " not found"));
        executionPlanRepository.delete(executionPlan);
        log.info("🗑️ ExecutionPlan deleted: {}", id);
    }
}
