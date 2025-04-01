package com.example.ASM.dto.request.OrderDetail;

import lombok.Data;

@Data
public class OrderDetailRequest {
    private int productId;
    private int quantity;
    private double currentPrice; // Thêm giá hiện tại của sản phẩm
}
