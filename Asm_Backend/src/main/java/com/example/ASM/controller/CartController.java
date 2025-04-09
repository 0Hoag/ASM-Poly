package com.example.ASM.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.Cart.CartRequest;
import com.example.ASM.dto.response.cart.CartResponse;
import com.example.ASM.service.CartService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {
    CartService cartService;

    @PostMapping("/")
    public ApiResponse<Boolean> create(@RequestBody @Valid CartRequest request) {
        return ApiResponse.<Boolean>builder()
                .code(1000)
                .result(cartService.Create(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CartResponse> detail(@PathVariable("id") int id) {
        return ApiResponse.<CartResponse>builder()
                .code(1000)
                .result(cartService.Detail(id))
                .build();
    }

    @GetMapping("/List")
    public ApiResponse<List<CartResponse>> list() {
        return ApiResponse.<List<CartResponse>>builder()
                .code(1000)
                .result(cartService.List())
                .build();
    }

    @GetMapping("/Get")
    public ApiResponse<PageResponse<CartResponse>> getPaged(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<CartResponse>>builder()
                .code(1000)
                .result(cartService.Get(page, size))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") int id) {
        cartService.Delete(id);
        return ApiResponse.<Void>builder().code(1000).message("Delete success!").build();
    }
}
