package com.example.ASM.controller;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.Order.OrderRequest;
import com.example.ASM.dto.request.Order.OrderUpdateRequest;
import com.example.ASM.dto.response.OrderResponse;
import com.example.ASM.service.OrderService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@RequestBody @Valid OrderRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .code(1000)
                .result(orderService.createOrder(request))  // Gọi phương thức createOrder
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderResponse> Detail(@PathVariable("id") int id) {
        return ApiResponse.<OrderResponse>builder()
                .code(1000)
                .result(orderService.getOrderById(id))
                .build();
    }

    @GetMapping("/List")
    public ApiResponse<List<OrderResponse>> List() {
        return ApiResponse.<List<OrderResponse>>builder()
                .code(1000)
                .result(orderService.getAllOrders())
                .build();
    }

    @GetMapping("/Get")
    public ApiResponse<PageResponse<OrderResponse>> Get(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        return ApiResponse.<PageResponse<OrderResponse>>builder()
                .code(1000)
                .result(orderService.getOrders(page, size))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderResponse> updateOrder(@PathVariable("id") int id, @RequestBody OrderUpdateRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .code(1000)
                .result(orderService.updateOrder(id, request))  // Gọi phương thức updateOrder
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> Delete(@PathVariable("id") int id) {
        orderService.deleteOrder(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Delete success!")
                .build();
    }
}
