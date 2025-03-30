package com.example.ASM.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    private int id;
    private String productName;
    private String description;
    private double price;
    private double salePrice;
    private int soldQuantity;
    private int stockQuantity;
    private Timestamp createdAt;
    private int category;
    private int productType;
    private List<String> imageUrls;
}
