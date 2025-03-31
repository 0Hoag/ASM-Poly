package com.example.ASM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ASM.entity.SpecificationType;

@Repository
public interface SpecificationTypeRepository extends JpaRepository<SpecificationType, Integer> {
    boolean existsBySpecName(String specName);
}
