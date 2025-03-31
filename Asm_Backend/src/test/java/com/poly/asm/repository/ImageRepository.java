package com.poly.asm.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.asm.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findFirstByProductId(int productId);
}