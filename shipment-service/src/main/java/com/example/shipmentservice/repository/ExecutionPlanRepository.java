package com.example.shipmentservice.repository;

import com.example.shipmentservice.model.ExecutionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExecutionPlanRepository extends JpaRepository<ExecutionPlan, Long> {
    @Query("select ex from ExecutionPlan ex where ex.shipment.id = :shipmentId")
    Optional<ExecutionPlan> findByShipmentId(@Param("shipmentId") Long shipmentId);

}