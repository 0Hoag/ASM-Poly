package com.example.ASM.service.build;

import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationRequest;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationUpdateRequest;
import com.example.ASM.entity.ProductSpecification;
import com.example.ASM.entity.SpecificationType;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.ProductSpecificationMapper;
import com.example.ASM.repository.SpecificationTypeRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Builder
@Service
@Slf4j
@FieldDefaults(makeFinal = true)
public class ProductSpecificationBuilder {
//    ProductSpecificationRepository productSpecificationRepository;
    SpecificationTypeRepository specificationTypeRepository;
    ProductSpecificationMapper productSpecificationMapper;

    public void processRequest(ProductSpecificationRequest request) {
        if (request.getName() == null || request.getValue() == null) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        if (!specificationTypeRepository.existsById(request.getSpecificationTypeId())) {
            throw new AppException(ErrorCode.SPECIFICATION_TYPE_NOT_EXISTED);
        }
    }

    public void processUpdateRequest(ProductSpecificationUpdateRequest request) {
        if (request.getName() == null || request.getValue() == null) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        if (!specificationTypeRepository.existsById(request.getSpecificationTypeId())) {
            throw new AppException(ErrorCode.SPECIFICATION_TYPE_NOT_EXISTED);
        }
    }

    public ProductSpecification toProductSpecification(ProductSpecificationRequest request) {
        // Ánh xạ từ ProductSpecificationRequest sang ProductSpecification
        SpecificationType specificationType = specificationTypeRepository.findById(request.getSpecificationTypeId())
                .orElseThrow(() -> new AppException(ErrorCode.SPECIFICATION_TYPE_NOT_EXISTED));

        ProductSpecification productSpecification = productSpecificationMapper.toProductSpecification(request);
        productSpecification.setSpecificationType(specificationType); // Set SpecificationType vào ProductSpecification

        return productSpecification;
    }

    public ProductSpecification toProductSpecification(ProductSpecificationUpdateRequest request, ProductSpecification existingProductSpecification) {
        // Ánh xạ từ ProductSpecificationUpdateRequest sang ProductSpecification
        SpecificationType specificationType = specificationTypeRepository.findById(request.getSpecificationTypeId())
                .orElseThrow(() -> new AppException(ErrorCode.SPECIFICATION_TYPE_NOT_EXISTED));

        existingProductSpecification.setName(request.getName());
        existingProductSpecification.setValue(request.getValue());
        existingProductSpecification.setSpecificationType(specificationType); // Set lại SpecificationType

        return existingProductSpecification;
    }
}
