package com.example.ASM.repository;

import com.example.ASM.entity.Cart;
import com.example.ASM.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
