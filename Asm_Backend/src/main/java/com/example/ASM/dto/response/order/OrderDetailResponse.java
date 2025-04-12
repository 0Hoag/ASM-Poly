package com.example.ASM.dto.response.order;

import lombok.Data;

@Data
public class OrderDetailResponse {
    private int id;
    private String productName;
    private double currentPrice;
    private int quantity;
    private int orderId;
}
