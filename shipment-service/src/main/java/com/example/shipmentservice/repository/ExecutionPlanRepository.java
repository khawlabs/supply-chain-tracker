package com.example.shipmentservice.repository;

import com.example.shipmentservice.model.ExecutionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionPlanRepository extends JpaRepository<ExecutionPlan, Long> {
}