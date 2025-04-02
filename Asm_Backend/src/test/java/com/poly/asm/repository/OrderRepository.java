package com.poly.asm.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.asm.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	Page<Order> findByUserId(int userId,Pageable pageable);
}
