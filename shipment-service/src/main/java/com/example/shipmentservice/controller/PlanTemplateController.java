package com.example.shipmentservice.controller;

import com.example.shipmentservice.dto.PlanTemplateDto;
import com.example.shipmentservice.mapper.PlanTemplateMapper;
import com.example.shipmentservice.model.PlanTemplate;
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
@RequestMapping("/api/plan-templates")
@RequiredArgsConstructor
public class PlanTemplateController {

    private final PlanTemplateService planTemplateService;
    private final PlanTemplateMapper planTemplateMapper;

    @PostMapping
    public ResponseEntity<?> create( @RequestBody PlanTemplateDto planTemplateDto) {
        if ( planTemplateDto == null ) {
            return new ResponseEntity<>( "", HttpStatus.BAD_REQUEST );
        }
        try {
          PlanTemplate plan =  planTemplateMapper.dtoToEntity(planTemplateDto);
            PlanTemplate planCreated = planTemplateService.createPlanTemplate( plan);
            PlanTemplateDto planDto= planTemplateMapper.entityToDto( planCreated );
            return new ResponseEntity<>( planDto, HttpStatus.CREATED );
        }
        catch ( Exception e ) {
            log.error( "Exception when calling createDevice {}", e );
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<PlanTemplate> planTemplates = planTemplateService.getAllPlanTemplates();

            if ( CollectionUtils.isEmpty( planTemplates ) ) {
                return ResponseEntity.noContent().build();
            } else {
                List<PlanTemplateDto> planTemplateDtos = planTemplateMapper.listEntityToDto( planTemplates );
                return ResponseEntity.ok( planTemplateDtos );
            }
        }
        catch ( Exception e ) {
            log.error( "Exception when calling getDevices {}", e );
            return new ResponseEntity<>( e, HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById( @PathVariable Long id ) {
        if ( id == null ) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
        try {
            PlanTemplate planTemplate = planTemplateService.getByIdPlanTemplate(id);
            PlanTemplateDto dto = planTemplateMapper.entityToDto(planTemplate);
            return new ResponseEntity<>( dto, HttpStatus.OK );
        }
        catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch ( Exception e ) {
            log.error( "Exception when calling getDeviceById {}", e );
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update( @PathVariable Long id, @RequestBody PlanTemplateDto dto ) {
        if ( dto == null || id == null ) {
            return ResponseEntity.badRequest().build();
        }
        try {
            PlanTemplate planTemplateUpdated = planTemplateService.updatePlanTemplate( id, dto );
            PlanTemplateDto planTemplate = planTemplateMapper.entityToDto( planTemplateUpdated );
            return new ResponseEntity<>( planTemplate, HttpStatus.ACCEPTED );

        } catch ( Exception e ) {
            log.error( "Exception when calling updateShipment {}", e );
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            planTemplateService.deletePlanTemplate(id);
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // HTTP 404
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // HTTP 500
        }
    }
}
