package com.example.ASM.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.OrderDetail.OrderDetailRequest;
import com.example.ASM.dto.request.OrderDetail.OrderDetailUpdateRequest;
import com.example.ASM.dto.response.order.OrderDetailResponse;
import com.example.ASM.service.OrderDetailService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/orders-detail")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailController {
    OrderDetailService orderDetailService;

    @PostMapping("/")
    public ApiResponse<Boolean> create(@RequestBody @Valid OrderDetailRequest request) {
        return ApiResponse.<Boolean>builder()
                .code(1000)
                .result(orderDetailService.Create(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderDetailResponse> detail(@PathVariable("id") int id) {
        return ApiResponse.<OrderDetailResponse>builder()
                .code(1000)
                .result(orderDetailService.Detail(id))
                .build();
    }

    @GetMapping("/GetByOrderId/{orderId}")
    public ApiResponse<List<OrderDetailResponse>> getByOrderId(@PathVariable("orderId") int orderId) {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .code(1000)
                .result(orderDetailService.GetByOrderId(orderId))
                .build();
    }

    @GetMapping("/List")
    public ApiResponse<List<OrderDetailResponse>> list() {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .code(1000)
                .result(orderDetailService.List())
                .build();
    }

    @GetMapping("/Get")
    public ApiResponse<PageResponse<OrderDetailResponse>> get(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<OrderDetailResponse>>builder()
                .code(1000)
                .result(orderDetailService.Get(page, size))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderDetailResponse> update(
            @PathVariable("id") int id, @RequestBody @Valid OrderDetailUpdateRequest request) {
        return ApiResponse.<OrderDetailResponse>builder()
                .code(1000)
                .result(orderDetailService.UpdateQuantity(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") int id) {
        orderDetailService.Delete(id);
        return ApiResponse.<Void>builder().code(1000).message("Delete success!").build();
    }
}
