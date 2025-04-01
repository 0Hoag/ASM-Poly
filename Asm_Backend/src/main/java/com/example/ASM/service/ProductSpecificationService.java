package com.example.ASM.service;


import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationRequest;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationUpdateRequest;
import com.example.ASM.dto.response.ProductSpecificationResponse;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.ProductSpecificationMapper;
import com.example.ASM.repository.ProductSpecificationRepository;
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
    ProductSpecificationRepository repository;
    ProductSpecificationMapper mapper;

    public boolean create(ProductSpecificationRequest request) {
        try {
            repository.save(mapper.toProductSpecification(request));
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
        return true;
    }


    public ProductSpecificationResponse detail(int id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SPECIFICATION_NOT_FOUND));
        return mapper.toProductSpecificationResponse(entity);
    }


    public List<ProductSpecificationResponse> list() {
        return repository.findAll().stream()
                .map(mapper::toProductSpecificationResponse)
                .collect(Collectors.toList());
    }

    public PageResponse<ProductSpecificationResponse> get(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = repository.findAll(pageable);

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

    public ProductSpecificationResponse update(int id, ProductSpecificationUpdateRequest request) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SPECIFICATION_NOT_FOUND));

        mapper.updateProductSpecification(entity, request);
        return mapper.toProductSpecificationResponse(repository.save(entity));
    }

    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new AppException(ErrorCode.SPECIFICATION_NOT_FOUND);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
    }
}
