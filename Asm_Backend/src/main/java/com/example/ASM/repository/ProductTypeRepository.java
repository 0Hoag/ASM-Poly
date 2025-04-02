package com.example.ASM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ASM.entity.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
    boolean existsByNameType(String nameType);
}
