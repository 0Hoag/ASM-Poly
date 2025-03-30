package com.example.ASM.repository;

import com.example.ASM.entity.CartDetail;
import com.example.ASM.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
}
