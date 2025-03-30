package com.example.ASM.repository;

import com.example.ASM.entity.Product;
import com.example.ASM.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
    boolean existsByNameType(String nameType);
}
