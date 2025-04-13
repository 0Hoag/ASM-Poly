package com.example.ASM.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.Product.ProductGetRequest;
import com.example.ASM.dto.request.Product.ProductRequest;
import com.example.ASM.dto.request.Product.ProductUpdateRequest;
import com.example.ASM.dto.response.product.ProductResponse;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.ProductMapper;
import com.example.ASM.repository.ProductRepository;
import com.example.ASM.service.build.ProductBuild;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository repo;
    ProductMapper mapper;
    ProductBuild productBuild;
    ImageService imageService;

    public ProductResponse Create(ProductRequest request) {
        productBuild.processRequest(request);

        if (repo.existsByProductName(request.getProductName())) {
            throw new AppException(ErrorCode.PRODUCT_NAME_EXISTED);
        }

        try {
            var product = repo.save(productBuild.buildProduct(request));
            return mapper.toProductResponse(product);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }

        //        return true;
    }

    public ProductResponse Detail(int id) {
        var product = repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        return mapper.toProductResponse(product);
    }

    public List<ProductResponse> List() {
        return repo.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }

    public PageResponse<ProductResponse> Get(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = repo.findAll(pageable);

        var data = pageData.getContent().stream().map(mapper::toProductResponse).collect(Collectors.toList());

        return PageResponse.<ProductResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .data(data)
                .build();
    }

    public List<ProductResponse> GetMany(ProductGetRequest request) {
        List<ProductResponse> productResponses = request.getProductIDs().stream()
                .map(id -> {
                    var product = repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
                    return mapper.toProductResponse(product);
                })
                .collect(Collectors.toList());

        return productResponses;
    }

    public ProductResponse Update(int id, ProductUpdateRequest request) {
        var product = repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        productBuild.processUpdateRequest(request);

        mapper.updateProduct(product, request);
        return mapper.toProductResponse(repo.save(product));
    }

    public void Delete(int id) {
        if (!repo.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
        }

        try {
            imageService.removeImageProduct(id);
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
    }
}
