package com.example.ASM.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.Cart.CartRequest;
import com.example.ASM.dto.response.cart.CartResponse;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.CartMapper;
import com.example.ASM.repository.CartRepository;
import com.example.ASM.repository.UserRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {
    CartRepository repo;
    CartMapper mapper;
    UserRepository userRepository;

    public boolean Create(CartRequest request) {
        if (request.getUser() == 0) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        userRepository.findById(request.getUser()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        try {
            var cart = mapper.toCart(request);
            cart.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            repo.save(cart);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
        return true;
    }

    public CartResponse Detail(int id) {
        var cart = repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_EXISTED));
        return mapper.toCartResponse(cart);
    }

    public List<CartResponse> List() {
        return repo.findAll().stream().map(mapper::toCartResponse).collect(Collectors.toList());
    }

    public PageResponse<CartResponse> Get(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = repo.findAll(pageable);

        var data = pageData.getContent().stream().map(mapper::toCartResponse).collect(Collectors.toList());

        return PageResponse.<CartResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .data(data)
                .build();
    }

    public void Delete(int id) {
        if (!repo.existsById(id)) {
            throw new AppException(ErrorCode.CART_NOT_EXISTED);
        }

        repo.deleteById(id);
    }
}
