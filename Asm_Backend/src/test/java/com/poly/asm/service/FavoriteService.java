package com.poly.asm.service;

import java.sql.Timestamp;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.asm.dto.PageResponse;
import com.poly.asm.dto.response.favorite.FavoriteResponse;
import com.poly.asm.entity.FavoriteProduct;
import com.poly.asm.entity.Product;
import com.poly.asm.entity.User;
import com.poly.asm.exception.AppException;
import com.poly.asm.exception.ErrorCode;
import com.poly.asm.mapper.FavoriteMapper;
import com.poly.asm.repository.FavoriteRepository;
import com.poly.asm.repository.ProductRepository;
import com.poly.asm.repository.UserReponsitory;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FavoriteService{
	FavoriteRepository favoriteRepository;
	UserReponsitory userRepository;
	ProductRepository productRepository;
	FavoriteMapper favoriteMapper;
	
	
	public PageResponse<FavoriteResponse> getFavoriteUser(int userId, int page, int size) {
	   
	    Pageable pageable = PageRequest.of(page -1 , size);
	    var pageData = favoriteRepository.findByUserId(userId,pageable);
	    
	    var data = pageData.getContent().stream()
	    		.map(favoriteMapper::toFavoriteResponse)
	    		.collect(Collectors.toList());
		return PageResponse.<FavoriteResponse>builder()
				.currentPage(page)
				.totalPages(pageData.getTotalPages())
				.pageSize(pageData.getSize())
				.totalElements(pageData.getTotalElements())
				.data(data)
				.build();
	}

	@Transactional 
	public String toggleLike(int userId, int productId) {
		var favorite = favoriteRepository.findByUserIdAndProductId(userId, productId);
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
