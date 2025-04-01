package com.example.ASM.repository;

import com.example.ASM.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    // Tìm danh sách OrderDetail theo orderId
    List<OrderDetail> findByOrderId(int orderId);
}
