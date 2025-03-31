package com.poly.asm.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.asm.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
}
