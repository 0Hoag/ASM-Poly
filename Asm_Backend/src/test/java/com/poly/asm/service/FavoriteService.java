package com.poly.asm.service;

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
import com.poly.asm.exception.AppException;
import com.poly.asm.exception.ErrorCode;
import com.poly.asm.mapper.ProductMapper;
import com.poly.asm.repository.FavoriteRepository;
import com.poly.asm.repository.ImageRepository;
import com.poly.asm.repository.ProductRepository;
import com.poly.asm.repository.UserReponsitory;

import jakarta.transaction.Transactional;
@Service

public class FavoriteService{
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
	
	
	public List<ProductResponse> getFavoriteUser(int userId) {
	    User user = userRepository.findById(userId)
	    		.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
	    List<FavoriteProduct> favorites = favoriteRepository.findByUser(user);
		return favorites.stream()
				.map(fav -> productMapper.toProductResponse(fav.getProduct()))
                .collect(Collectors.toList());
	}

	@Transactional 
	public String toggleLike(int userId, int productId) {
		 Optional<FavoriteProduct> favorite = favoriteRepository.findByUserIdAndProductId(userId, productId);
		 if (favorite.isPresent()) {
	            favoriteRepository.deleteByUserIdAndProductId(userId, productId);
	            return "Bỏ thích thành công";
		 } else {
	            User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
	            Product product = productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
	            
	            FavoriteProduct newFavorite = new FavoriteProduct();
	            newFavorite.setUser(user);
	            newFavorite.setProduct(product);
	            newFavorite.setLikedAt(new Timestamp(System.currentTimeMillis()));
	            favoriteRepository.save(newFavorite);
	            return "Thích thành công";
	        }
	}
}
