package com.example.ASM.repository;

import com.example.ASM.entity.Category;
import com.example.ASM.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByProductName(String productName);
}
