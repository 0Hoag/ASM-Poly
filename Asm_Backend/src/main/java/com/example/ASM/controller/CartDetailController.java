package com.example.ASM.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.CartDetail.CartDetailRequest;
import com.example.ASM.dto.response.cart.CartDetailResponse;
import com.example.ASM.service.CartDetailService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cart-detail")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartDetailController { // chua test duoc tai vi can cartID
    CartDetailService cartDetailService;

    @PostMapping("/")
    public ApiResponse<Boolean> create(@RequestBody @Valid CartDetailRequest request) {
        return ApiResponse.<Boolean>builder()
                .code(1000)
                .result(cartDetailService.Create(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CartDetailResponse> detail(@PathVariable("id") int id) {
        return ApiResponse.<CartDetailResponse>builder()
                .code(1000)
                .result(cartDetailService.Detail(id))
                .build();
    }

    @GetMapping("/List")
    public ApiResponse<List<CartDetailResponse>> list() {
        return ApiResponse.<List<CartDetailResponse>>builder()
                .code(1000)
                .result(cartDetailService.List())
                .build();
    }

    @GetMapping("/Get")
    public ApiResponse<PageResponse<CartDetailResponse>> getPaged(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<CartDetailResponse>>builder()
                .code(1000)
                .result(cartDetailService.Get(page, size))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CartDetailResponse> update(
            @PathVariable("id") int id,
            @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity) {
        return ApiResponse.<CartDetailResponse>builder()
                .code(1000)
                .result(cartDetailService.UpdateQuantity(id, quantity))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") int id) {
        cartDetailService.Delete(id);
        return ApiResponse.<Void>builder().code(1000).message("Delete success!").build();
    }
}
