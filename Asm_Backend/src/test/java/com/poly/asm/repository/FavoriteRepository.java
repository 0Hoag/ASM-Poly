package com.poly.asm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.asm.entity.FavoriteProduct;
import com.poly.asm.entity.User;

public interface FavoriteRepository extends JpaRepository<FavoriteProduct, Integer> {
	List<FavoriteProduct> findByUser(User User);

    Optional<FavoriteProduct> findByUserIdAndProductId(int userId, int productId);

    void deleteByUserIdAndProductId(int userId, int productId);

    boolean existsByUserIdAndProductId(int userId, int productId);
}
