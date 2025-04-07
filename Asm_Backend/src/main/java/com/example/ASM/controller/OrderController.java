package com.example.ASM.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.Order.OrderRequest;
import com.example.ASM.dto.request.Order.OrderUpdateRequest;
import com.example.ASM.dto.response.order.OrderResponse;
import com.example.ASM.service.OrderService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController { // chua test duoc vi can userID va AddressID
    OrderService orderService;

    @PostMapping("/")
    public ApiResponse<Boolean> create(@RequestBody @Valid OrderRequest request) {
        return ApiResponse.<Boolean>builder()
                .code(1000)
                .result(orderService.Create(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderResponse> detail(@PathVariable("id") int id) {
        return ApiResponse.<OrderResponse>builder()
                .code(1000)
                .result(orderService.Detail(id))
                .build();
    }

    @GetMapping("/List")
    public ApiResponse<List<OrderResponse>> list() {
        return ApiResponse.<List<OrderResponse>>builder()
                .code(1000)
                .result(orderService.List())
                .build();
    }

    @GetMapping("/Get")
    public ApiResponse<PageResponse<OrderResponse>> getPaged(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<OrderResponse>>builder()
                .code(1000)
                .result(orderService.Get(page, size))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderResponse> update(
            @PathVariable("id") int id, @RequestBody @Valid OrderUpdateRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .code(1000)
                .result(orderService.Update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") int id) {
        orderService.Delete(id);
        return ApiResponse.<Void>builder().code(1000).message("Delete success!").build();
    }
}
