package com.example.ASM.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.ProductType.ProductTypeRequest;
import com.example.ASM.dto.response.product.ProductTypeResponse;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.ProductTypeMapper;
import com.example.ASM.repository.ProductTypeRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductTypeService {
    ProductTypeMapper mapper;
    ProductTypeRepository repo;

    public ProductTypeResponse Create(ProductTypeRequest request) {
        if (request.getNameType().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        if (repo.existsByNameType(request.getNameType())) {
            throw new AppException(ErrorCode.PRODUCT_TYPE_NAME_EXISTED);
        }

        try {
            var productType = repo.save(mapper.toProductType(request));
            return mapper.toProductTypeResponse(productType);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
    }

    public ProductTypeResponse Detail(int id) {
        var productType = repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_TYPE_NOT_EXISTED));
        return mapper.toProductTypeResponse(productType);
    }

    public PageResponse<ProductTypeResponse> Get(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = repo.findAll(pageable);

        var data = pageData.getContent().stream()
                .map(mapper::toProductTypeResponse)
                .collect(Collectors.toList());

        return PageResponse.<ProductTypeResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .data(data)
                .build();
    }

    public List<ProductTypeResponse> List() {
        return repo.findAll().stream().map(mapper::toProductTypeResponse).collect(Collectors.toList());
    }

    public ProductTypeResponse Update(int id, ProductTypeRequest request) {
        var productType = repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_TYPE_NOT_EXISTED));

        if (request.getNameType().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        productType.setNameType(request.getNameType());

        return mapper.toProductTypeResponse(repo.save(productType));
    }

    public void Delete(int id) {
        if (!repo.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_TYPE_NOT_EXISTED);
        }

        repo.deleteById(id);
    }
}
