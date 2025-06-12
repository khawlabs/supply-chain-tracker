package com.example.shipmentservice.controller;

import com.example.shipmentservice.dto.ExecutionPlanDto;
import com.example.shipmentservice.dto.ShipmentDto;
import com.example.shipmentservice.mapper.ExecutionPlanMapper;
import com.example.shipmentservice.mapper.PlanTemplateMapper;
import com.example.shipmentservice.model.ExecutionPlan;
import com.example.shipmentservice.model.Shipment;
import com.example.shipmentservice.service.ExecutionPlanService;
import com.example.shipmentservice.service.PlanTemplateService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/execution-plan")
@RequiredArgsConstructor
public class ExecutionPlanController {

    private final ExecutionPlanService executionPlanService;
    private final ExecutionPlanMapper executionPlanMapper;

    @PostMapping
    public ResponseEntity<?> createExecutionPlan(@RequestBody ExecutionPlanDto executionPlanDto ) {
        if ( executionPlanDto == null ) {
            return new ResponseEntity<>( "", HttpStatus.BAD_REQUEST );
        }
        try {
            ExecutionPlan executionPlanCreated = executionPlanService.create( executionPlanDto);
            ExecutionPlanDto executionPlan= executionPlanMapper.entityToDto( executionPlanCreated );
            return new ResponseEntity<>( executionPlan, HttpStatus.CREATED );
        }
        catch ( Exception e ) {
            log.error( "Exception when calling create executionPlan {}", e );
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
    @PutMapping
    public ResponseEntity<?> updateExecutionPlan( Long id, ExecutionPlanDto executionPlanDto ) {
        if ( executionPlanDto == null || id == null ) {
            return ResponseEntity.badRequest().build();
        }
        try {
            ExecutionPlan executionPlanUpdated = executionPlanService.update( id, executionPlanDto );
            ExecutionPlanDto executionPlan = executionPlanMapper.entityToDto( executionPlanUpdated );
            return new ResponseEntity<>( executionPlan, HttpStatus.ACCEPTED );
        } catch ( Exception e ) {
            log.error( "Exception when calling update Execution Plan {}", e );
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllExecutionPlan() {
        try {
            List<ExecutionPlan> executionPlans = executionPlanService.getAllExecutionPlans();

            if ( CollectionUtils.isEmpty( executionPlans ) ) {
                return ResponseEntity.noContent().build();
            } else {
                List<ExecutionPlanDto> executionPlanDtos = executionPlanMapper.listEntityToDto( executionPlans );
                return ResponseEntity.ok( executionPlanDtos );
            }
        }
        catch ( Exception e ) {
            log.error( "Exception when calling get Execution Plans {}", e );
            return new ResponseEntity<>( e, HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getExecutionPlanById( @PathVariable Long id ) {
        if ( id == null ) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
        try {
            ExecutionPlan executionPlan = executionPlanService.getExecutionPlanById(id);
            ExecutionPlanDto dto = executionPlanMapper.entityToDto(executionPlan);
            return new ResponseEntity<>( dto, HttpStatus.OK );
        }
        catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch ( Exception e ) {
            log.error( "Exception when calling get Execution Plan By Id {}", e );
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExecutionPlan(@PathVariable Long id) {
        try {
            executionPlanService.deleteExecutionPlan(id);
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // HTTP 404
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // HTTP 500
        }
    }
}
