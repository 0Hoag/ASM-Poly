package com.example.ASM.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationRequest;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationUpdateRequest;
import com.example.ASM.dto.response.product.ProductSpecificationResponse;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.ProductSpecificationMapper;
import com.example.ASM.repository.ProductSpecificationRepository;
import com.example.ASM.service.build.ProductSpecificationBuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductSpecificationService {
    ProductSpecificationMapper mapper;
    ProductSpecificationRepository repo;
    ProductSpecificationBuilder builder;

    public boolean Create(ProductSpecificationRequest request) {
        builder.processRequest(request);

        try {
            repo.save(mapper.toProductSpecification(request));
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }

        return true;
    }

    public ProductSpecificationResponse Detail(int id) {
        var productSpecification =
                repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.SPECIFICATION_NOT_FOUND));
        return mapper.toProductSpecificationResponse(productSpecification);
    }

    public ProductSpecificationResponse GetByName(String name) {
        var productSpecification = repo.findByName(name);
        return mapper.toProductSpecificationResponse(productSpecification);
    }

    public List<ProductSpecificationResponse> List() {
        return repo.findAll().stream()
                .map(mapper::toProductSpecificationResponse)
                .collect(Collectors.toList());
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

    public ProductSpecificationResponse Update(int id, ProductSpecificationUpdateRequest request) {
        var productSpecification =
                repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.SPECIFICATION_NOT_FOUND));
        builder.processUpdateRequest(request);
        mapper.updateProductSpecification(productSpecification, request);
        return mapper.toProductSpecificationResponse(repo.save(productSpecification));
    }

    public void Delete(int id) {
        if (!repo.existsById(id)) {
            throw new AppException(ErrorCode.SPECIFICATION_NOT_FOUND);
        }

        repo.deleteById(id);
    }
}
