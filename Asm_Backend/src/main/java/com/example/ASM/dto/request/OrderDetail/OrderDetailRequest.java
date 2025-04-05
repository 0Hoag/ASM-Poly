package com.example.ASM.dto.request.OrderDetail;

import lombok.Data;

@Data
public class OrderDetailRequest {
    private int orderId;
    private int productId;
    private int quantity;
    private double currentPrice;
}
