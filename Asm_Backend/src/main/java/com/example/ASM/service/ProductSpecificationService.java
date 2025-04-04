package com.example.ASM.service;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationRequest;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationUpdateRequest;
import com.example.ASM.dto.response.ProductSpecificationResponse;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.ProductSpecificationMapper;
import com.example.ASM.repository.ProductSpecificationRepository;
import com.example.ASM.repository.SpecificationTypeRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductSpecificationService {
    ProductSpecificationMapper mapper;
    ProductSpecificationRepository repo;
    SpecificationTypeRepository specificationTypeRepository;

    public boolean Create(ProductSpecificationRequest request) {
        if (request.getName().isEmpty() || request.getValue().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        specificationTypeRepository.findById(request.getSpecificationTypeId())
                .orElseThrow(() -> new AppException(ErrorCode.SPECIFICATION_TYPE_NOT_EXISTED));

        try {
            repo.save(mapper.toProductSpecification(request));
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }

        return true;
    }

    public ProductSpecificationResponse Detail(int id) {
        var productSpecification = repo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        return mapper.toProductSpecificationResponse(productSpecification);
    }

    public PageResponse<ProductSpecificationResponse> Get(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = repo.findAll(pageable);

        var data = pageData.getContent().stream()
                .map(mapper::toProductSpecificationResponse)
                .collect(Collectors.toList());

        return PageResponse.<ProductSpecificationResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .data(data)
                .build();
    }

    public List<ProductSpecificationResponse> List() {
        return repo.findAll().stream()
                .map(mapper::toProductSpecificationResponse)
                .collect(Collectors.toList());
    }

    public ProductSpecificationResponse Update(int id, ProductSpecificationUpdateRequest request) {
        var productSpecification = repo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        if (request.getName().isEmpty() || request.getValue().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        productSpecification.setName(request.getName());
        productSpecification.setValue(request.getValue());

        specificationTypeRepository.findById(request.getSpecificationTypeId())
                .orElseThrow(() -> new AppException(ErrorCode.SPECIFICATION_TYPE_NOT_EXISTED));

        return mapper.toProductSpecificationResponse(repo.save(productSpecification));
    }

    public void Delete(int id) {
        if (!repo.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
        }

        repo.deleteById(id);
    }
}
