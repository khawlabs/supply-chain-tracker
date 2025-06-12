package com.example.shipmentservice.repository;

import com.example.shipmentservice.model.PlanTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanTemplateRepository extends JpaRepository<PlanTemplate, Long> {
}