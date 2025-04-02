package com.example.ASM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ASM.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {}
