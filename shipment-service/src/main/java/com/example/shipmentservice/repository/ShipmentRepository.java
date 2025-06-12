package com.example.shipmentservice.repository;

import com.example.shipmentservice.model.Shipment;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Shipment s where s.shipmentId = :shipmentId")
    Optional<Shipment> findByShipmentIdForUpdate(@Param("shipmentId") String shipmentId);
    Optional<Shipment> findByShipmentId(String shipmentId);
}
