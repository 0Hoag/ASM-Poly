package com.example.ASM.service.build;

import org.springframework.stereotype.Service;

import com.example.ASM.dto.request.SpecificationType.SpecificationTypeRequest;
import com.example.ASM.dto.request.SpecificationType.SpecificationTypeUpdateRequest;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.SpecificationTypeMapper;
import com.example.ASM.repository.ProductTypeRepository;

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
public class SpecificationTypeBuilder {
    ProductTypeRepository productTypeRepository;
    SpecificationTypeMapper specificationTypeMapper;

    public void processRequest(SpecificationTypeRequest request) {
        if (request.getProductType() == 0 || request.getSpecName() == null) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        if (!productTypeRepository.existsById(request.getProductType())) {
            throw new AppException(ErrorCode.PRODUCT_TYPE_NOT_EXISTED);
        }
    }

    public void processUpdateRequest(SpecificationTypeUpdateRequest request) {
        if (request.getProductType() == 0 || request.getSpecName() == null) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        if (!productTypeRepository.existsById(request.getProductType())) {
            throw new AppException(ErrorCode.PRODUCT_TYPE_NOT_EXISTED);
        }
    }
}
