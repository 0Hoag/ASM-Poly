package com.example.ASM.repository;

import com.example.ASM.entity.Order;
import com.example.ASM.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
