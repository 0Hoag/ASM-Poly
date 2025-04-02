package com.poly.asm.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.asm.entity.FavoriteProduct;

public interface FavoriteRepository extends JpaRepository<FavoriteProduct, Integer> {
	Page<FavoriteProduct> findByUserId(int userId,Pageable pageable);

    Optional<FavoriteProduct> findByUserIdAndProductId(int userId, int productId);

    void deleteByUserIdAndProductId(int userId, int productId);

    boolean existsByUserIdAndProductId(int userId, int productId);
}
