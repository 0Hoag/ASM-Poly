package com.example.ASM.service;

import java.sql.Timestamp;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.response.user.FavoriteProductResponse;
import com.example.ASM.entity.FavoriteProduct;
import com.example.ASM.entity.Product;
import com.example.ASM.entity.User;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.FavoriteProductMapper;
import com.example.ASM.repository.FavoriteProductRepository;
import com.example.ASM.repository.ProductRepository;
import com.example.ASM.repository.UserRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FavoriteProductService {
    FavoriteProductRepository favoriteRepository;
    UserRepository userRepository;
    ProductRepository productRepository;
    FavoriteProductMapper favoriteMapper;

    public PageResponse<FavoriteProductResponse> getFavoriteUser(int userId, int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = favoriteRepository.findByUserId(userId, pageable);

        var data = pageData.getContent().stream()
                .map(favoriteMapper::toFavoriteResponse)
                .collect(Collectors.toList());
        return PageResponse.<FavoriteProductResponse>builder()
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
            Product product = productRepository
                    .findById(productId)
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

            FavoriteProduct newFavorite = new FavoriteProduct();
            newFavorite.setUser(user);
            newFavorite.setProduct(product);
            newFavorite.setLikedAt(new Timestamp(System.currentTimeMillis()));

            favoriteRepository.save(newFavorite);
            return "Thích thành công";
        }
    }
}
