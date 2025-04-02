package com.example.ASM.service.build;


import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationRequest;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationUpdateRequest;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.ProductSpecificationMapper;
import com.example.ASM.repository.ProductSpecificationRepository;
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
    ProductSpecificationRepository productSpecificationRepository;
    ProductSpecificationMapper productSpecificationMapper;

    public void processRequest(ProductSpecificationRequest request) {
        if (request.getName() == null || request.getValue() == null) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        if (!productSpecificationRepository.existsById(request.getSpecificationTypeId())) {
            throw new AppException(ErrorCode.SPECIFICATION_NOT_FOUND);
        }
    }

    public void processUpdateRequest(ProductSpecificationUpdateRequest request) {
        if (request.getName() == null || request.getValue() == null) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        if (!productSpecificationRepository.existsById(request.getSpecificationTypeId())) {
            throw new AppException(ErrorCode.SPECIFICATION_NOT_FOUND);
        }
    }
}
