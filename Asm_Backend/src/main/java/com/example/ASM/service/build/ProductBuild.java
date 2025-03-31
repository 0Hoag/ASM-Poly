package com.example.ASM.service.build;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ASM.dto.request.Product.ProductRequest;
import com.example.ASM.dto.request.Product.ProductUpdateRequest;
import com.example.ASM.entity.Product;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.ProductMapper;
import com.example.ASM.repository.CategoryRepository;
import com.example.ASM.repository.ProductTypeRepository;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@Builder
@Service
@Slf4j
@FieldDefaults(makeFinal = true)
public class ProductBuild {
    ProductMapper mapper;
    ProductTypeRepository productTypeRepository;
    CategoryRepository categoryRepository;

    public void processRequest(ProductRequest request) {
        if (request.getProductName() == null || request.getProductName().trim().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }
        if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }
        if (request.getPrice() <= 0) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }
        if (request.getStockQuantity() < 0) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        if (!categoryRepository.existsById(request.getCategory())) {
            throw new AppException(ErrorCode.CATEGORIES_NOT_EXISTED);
        }

        if (!productTypeRepository.existsById(request.getProductType())) {
            throw new AppException(ErrorCode.PRODUCT_TYPE_NOT_EXISTED);
        }
    }

    public void processUpdateRequest(ProductUpdateRequest request) {
        if (request.getProductName() == null || request.getProductName().trim().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }
        if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }
        if (request.getPrice() <= 0) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }
        if (request.getStockQuantity() < 0) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        if (!categoryRepository.existsById(request.getCategory())) {
            throw new AppException(ErrorCode.CATEGORIES_NOT_EXISTED);
        }

        if (!productTypeRepository.existsById(request.getProductType())) {
            throw new AppException(ErrorCode.PRODUCT_TYPE_NOT_EXISTED);
        }
    }

    public Product buildProduct(ProductRequest request) {
        Product product = mapper.toProduct(request);
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        product.setImages(List.of());
        return product;
    }
}
