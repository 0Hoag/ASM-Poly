package com.example.ASM.dto.response;
import lombok.Data;

@Data
public class OrderDetailResponse {
    private int productId;
    private String productName;
    private double currentPrice;
    private int quantity;
}
