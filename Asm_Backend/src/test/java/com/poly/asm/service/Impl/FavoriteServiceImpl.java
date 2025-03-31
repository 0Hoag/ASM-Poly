package com.poly.asm.service.Impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm.dto.response.product.ProductResponse;
import com.poly.asm.entity.FavoriteProduct;
import com.poly.asm.entity.Product;
import com.poly.asm.entity.User;
import com.poly.asm.mapper.ProductMapper;
import com.poly.asm.repository.FavoriteRepository;
import com.poly.asm.repository.ImageRepository;
import com.poly.asm.repository.ProductRepository;
import com.poly.asm.repository.UserReponsitory;
import com.poly.asm.service.FavoriteService;

import jakarta.transaction.Transactional;
@Service

public class FavoriteServiceImpl implements FavoriteService{
	@Autowired
	FavoriteRepository favoriteRepository;
	@Autowired
	UserReponsitory userRepository;
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductMapper productMapper;
	
	
	@Override
	public List<ProductResponse> getFavoriteUser(int userId) {
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại!"));
	    List<FavoriteProduct> favorites = favoriteRepository.findByUser(user);
		return favorites.stream()
				.map(fav -> productMapper.toProductResponse(fav.getProduct()))
                .collect(Collectors.toList());
	}

	@Override
	@Transactional 
	public String toggleLike(int userId, int productId) {
		 Optional<FavoriteProduct> favorite = favoriteRepository.findByUserIdAndProductId(userId, productId);
		 if (favorite.isPresent()) {
	            favoriteRepository.deleteByUserIdAndProductId(userId, productId);
	            return "Bỏ thích thành công";
		 } else {
	            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User không tồn tại"));
	            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
	            
	            FavoriteProduct newFavorite = new FavoriteProduct();
	            newFavorite.setUser(user);
	            newFavorite.setProduct(product);
	            newFavorite.setLikedAt(new Timestamp(System.currentTimeMillis()));
	            favoriteRepository.save(newFavorite);
	            return "Thích thành công";
	        }
	}
}
