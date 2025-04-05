package com.example.ASM.service;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.response.user.FavoriteResponse;
import com.example.ASM.entity.FavoriteProduct;
import com.example.ASM.entity.Product;
import com.example.ASM.entity.User;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.FavoriteMapper;
import com.example.ASM.repository.FavoriteRepository;
import com.example.ASM.repository.ProductRepository;
import com.example.ASM.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FavoriteService {
	FavoriteRepository favoriteRepository;
	UserRepository userRepository;
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
