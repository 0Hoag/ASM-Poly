package com.example.ASM.repository;

import com.example.ASM.entity.FavoriteProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<FavoriteProduct, Integer> {
	Page<FavoriteProduct> findByUserId(int userId,Pageable pageable);

    Optional<FavoriteProduct> findByUserIdAndProductId(int userId, int productId);

    void deleteByUserIdAndProductId(int userId, int productId);

    boolean existsByUserIdAndProductId(int userId, int productId);
}
