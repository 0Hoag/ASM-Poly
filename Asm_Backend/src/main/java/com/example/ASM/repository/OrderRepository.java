package com.example.ASM.repository;

import com.example.ASM.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
    List<Order> findByUserId(int userId); // Tìm đơn hàng theo User
}
