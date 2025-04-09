package com.example.ASM.service.build;

import org.springframework.stereotype.Service;

import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationRequest;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationUpdateRequest;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.ProductSpecificationMapper;
import com.example.ASM.repository.ProductRepository;
import com.example.ASM.repository.SpecificationTypeRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@Builder
@Service
@Slf4j
@FieldDefaults(makeFinal = true)
public class ProductSpecificationBuilder {
    SpecificationTypeRepository specificationTypeRepository;
    ProductRepository productRepository;
    ProductSpecificationMapper productSpecificationMapper;

    public void processRequest(ProductSpecificationRequest request) {
        if (request.getName() == null || request.getValue() == null) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        if (!specificationTypeRepository.existsById(request.getSpecificationTypeId())) {
            throw new AppException(ErrorCode.SPECIFICATION_TYPE_NOT_EXISTED);
        }

        if (!productRepository.existsById(request.getProductID())) {
            throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
        }
    }

    public void processUpdateRequest(ProductSpecificationUpdateRequest request) {
        if (request.getName() == null || request.getValue() == null) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        if (!specificationTypeRepository.existsById(request.getSpecificationTypeId())) {
            throw new AppException(ErrorCode.SPECIFICATION_TYPE_NOT_EXISTED);
        }

        if (!productRepository.existsById(request.getProductID())) {
            throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
        }
    }
}
