package com.example.ASM.controller;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.request.OrderDetail.OrderDetailRequest;
import com.example.ASM.dto.request.OrderDetail.OrderDetailUpdateRequest;
import com.example.ASM.dto.response.OrderDetailResponse;
import com.example.ASM.service.OrderDetailService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-detail")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailController {

    OrderDetailService orderDetailService;

    /**
     * Tạo mới OrderDetail cho một Order (nếu đã tồn tại sản phẩm thì cộng dồn số lượng)
     */
    @PostMapping("/{orderId}")
    public ApiResponse<OrderDetailResponse> create(
            @PathVariable("orderId") int orderId,
            @RequestBody @Valid OrderDetailRequest request
    ) {
        return ApiResponse.<OrderDetailResponse>builder()
                .code(1000)
                .result(orderDetailService.createOrderDetail(orderId, request))
                .build();
    }

    /**
     * Lấy OrderDetail theo ID
     */
    @GetMapping("/{id}")
    public ApiResponse<OrderDetailResponse> getById(@PathVariable("id") int id) {
        return ApiResponse.<OrderDetailResponse>builder()
                .code(1000)
                .result(orderDetailService.getOrderDetailById(id))
                .build();
    }

    /**
     * Lấy danh sách toàn bộ OrderDetail
     */
    @GetMapping("/list")
    public ApiResponse<List<OrderDetailResponse>> getAll() {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .code(1000)
                .result(orderDetailService.getAllOrderDetails())
                .build();
    }

    /**
     * Lấy danh sách OrderDetail theo orderId
     */
    @GetMapping("/by-order/{orderId}")
    public ApiResponse<List<OrderDetailResponse>> getByOrderId(@PathVariable("orderId") int orderId) {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .code(1000)
                .result(orderDetailService.getByOrderId(orderId))
                .build();
    }

    /**
     * Cập nhật số lượng OrderDetail
     */
    @PutMapping("/{id}")
    public ApiResponse<OrderDetailResponse> update(
            @PathVariable("id") int id,
            @RequestBody @Valid OrderDetailUpdateRequest request
    ) {
        return ApiResponse.<OrderDetailResponse>builder()
                .code(1000)
                .result(orderDetailService.updateOrderDetail(id, request))
                .build();
    }

    /**
     * Xóa OrderDetail theo ID
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") int id) {
        orderDetailService.deleteOrderDetail(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Delete success!")
                .build();
    }
}
