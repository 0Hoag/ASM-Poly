package com.example.ASM.dto.response;

import lombok.Data;

@Data
public class OrderDetailResponse {
    private String productName;
    private double currentPrice;
    private int quantity;
    private int orderId; // Nếu chỉ cần ID đơn hàng
}
