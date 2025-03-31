package com.poly.asm.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.asm.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	List<Order> findByUserId(int userId);
}
