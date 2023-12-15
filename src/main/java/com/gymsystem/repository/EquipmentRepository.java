package com.gymsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymsystem.model.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    // Custom queries, if needed
}
