package com.example.ASM.dto.request.Order;

import lombok.Data;

@Data
public class OrderUpdateRequest {
    private int addressId;            // ID của địa chỉ giao hàng mới
    private int orderStatusId;        // Trạng thái đơn hàng mới
    private double totalAmount;       // Tổng số tiền mới của đơn hàng
}
