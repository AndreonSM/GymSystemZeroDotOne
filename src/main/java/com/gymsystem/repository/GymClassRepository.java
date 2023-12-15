package com.gymsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymsystem.model.GymClass;

@Repository
public interface GymClassRepository extends JpaRepository<GymClass, Long> {
    // Custom queries, if needed
}
