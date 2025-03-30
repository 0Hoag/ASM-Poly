package com.example.ASM.repository;

import com.example.ASM.entity.ProductType;
import com.example.ASM.entity.SpecificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationTypeRepository extends JpaRepository<SpecificationType, Integer> {
    boolean existsBySpecName(String specName);
}
