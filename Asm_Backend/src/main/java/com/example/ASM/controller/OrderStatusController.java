package com.example.ASM.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.OrderStatus.OrderStatusRequest;
import com.example.ASM.dto.response.order.OrderStatusResponse;
import com.example.ASM.service.OrderStatusService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order-status")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderStatusController {
    OrderStatusService orderStatusService;

    @PostMapping("/")
    public ApiResponse<Boolean> create(@RequestBody @Valid OrderStatusRequest request) {
        return ApiResponse.<Boolean>builder()
                .code(1000)
                .result(orderStatusService.Create(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderStatusResponse> detail(@PathVariable("id") int id) {
        return ApiResponse.<OrderStatusResponse>builder()
                .code(1000)
                .result(orderStatusService.Detail(id))
                .build();
    }

    @GetMapping("/List")
    public ApiResponse<List<OrderStatusResponse>> list() {
        return ApiResponse.<List<OrderStatusResponse>>builder()
                .code(1000)
                .result(orderStatusService.List())
                .build();
    }

    @GetMapping("/Get")
    public ApiResponse<PageResponse<OrderStatusResponse>> getPaged(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<OrderStatusResponse>>builder()
                .code(1000)
                .result(orderStatusService.Get(page, size))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<Boolean> update(@PathVariable("id") int id, @RequestBody @Valid OrderStatusRequest request) {
        return ApiResponse.<Boolean>builder()
                .code(1000)
                .result(orderStatusService.Update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") int id) {
        orderStatusService.Delete(id);
        return ApiResponse.<Void>builder().code(1000).message("Delete success!").build();
    }
}
