package com.example.ASM.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ASM.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    // Tìm danh sách OrderDetail theo orderId
    List<OrderDetail> findByOrderId(int orderId);

    // Kiểm tra OrderDetail đã tồn tại với orderId và productId hay chưa
    Optional<OrderDetail> findByOrderIdAndProductId(int orderId, int productId);

    // Kiểm tra sự tồn tại của OrderDetail theo orderId và productId (trả về true/false)
    boolean existsByOrderIdAndProductId(int orderId, int productId);
}
