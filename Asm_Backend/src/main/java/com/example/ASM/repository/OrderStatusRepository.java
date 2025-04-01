package com.example.ASM.repository;

import com.example.ASM.entity.CartDetail;
import com.example.ASM.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
    boolean existsByCategoryName(String categoryName);
}
