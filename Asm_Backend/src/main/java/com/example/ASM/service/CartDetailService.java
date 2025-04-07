package com.example.ASM.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.CartDetail.CartDetailRequest;
import com.example.ASM.dto.response.cart.CartDetailResponse;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.CartDetailMapper;
import com.example.ASM.repository.CartDetailRepository;
import com.example.ASM.repository.CartRepository;
import com.example.ASM.repository.ProductRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartDetailService {
    ProductRepository productRepository;
    CartDetailRepository repo;
    CartRepository cartRepository;
    CartDetailMapper mapper;

    public boolean Create(CartDetailRequest request) {
        if (!cartRepository.existsById(request.getCart())) {
            throw new AppException(ErrorCode.CART_NOT_EXISTED);
        }

        if (!productRepository.existsById(request.getProduct())) {
            throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
        }

        productRepository
                .findById(request.getProduct())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        try {
            repo.save(mapper.toCartDetail(request));
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }

        return true;
    }

    public CartDetailResponse Detail(int id) {
        var cartDetail = repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.CART_DETAIL_NOT_EXISTED));
        return mapper.toCartDetailResponse(cartDetail);
    }

    public List<CartDetailResponse> List() {
        return repo.findAll().stream().map(mapper::toCartDetailResponse).collect(Collectors.toList());
    }

    public PageResponse<CartDetailResponse> Get(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = repo.findAll(pageable);

        var data =
                pageData.getContent().stream().map(mapper::toCartDetailResponse).collect(Collectors.toList());

        return PageResponse.<CartDetailResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .data(data)
                .build();
    }

    public CartDetailResponse UpdateQuantity(int id, int quantity) {
        var cartDetail = repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.CART_DETAIL_NOT_EXISTED));

        if (quantity == 0) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        cartDetail.setQuantity(quantity);

        return mapper.toCartDetailResponse(repo.save(cartDetail));
    }

    public void Delete(int id) {
        if (!repo.existsById(id)) {
            throw new AppException(ErrorCode.CART_DETAIL_NOT_EXISTED);
        }

        repo.deleteById(id);
    }
}
