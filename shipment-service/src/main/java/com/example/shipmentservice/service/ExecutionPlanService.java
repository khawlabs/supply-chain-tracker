package com.example.shipmentservice.service;

import com.example.shipmentservice.dto.ExecutionPlanDto;
import com.example.shipmentservice.exception.RessourceNotFoundException;
import com.example.shipmentservice.mapper.ExecutionPlanMapper;
import com.example.shipmentservice.model.ExecutionPlan;
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

    private final ExecutionPlanMapper executionPlanMapper;

    private static final String EXECUTION_PLAN_NOT_FOUND = "Shipment not found";

    @Transactional
    public ExecutionPlan create(ExecutionPlanDto executionPlanDto) throws Exception {
        try {
            ExecutionPlan executionPlan = executionPlanMapper.dtoToEntity( executionPlanDto );
            executionPlan.setStatus("CREATED");
            ExecutionPlan executionPlanSaved = executionPlanRepository.save(executionPlan);
            log.info("✅ ExecutionPlan created: {}", executionPlanSaved.getId());
            return executionPlanSaved;

        } catch (Exception e) {
            log.error("❌ Failed to create execution plan", e);
            throw new Exception("Failed to create execution plan", e);
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
