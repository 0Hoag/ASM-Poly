package com.example.ASM.dto.response.product;

import java.sql.Timestamp;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    int id;
    String productName;
    String description;
    double price;
    double salePrice;
    int soldQuantity;
    int stockQuantity;
    Timestamp createdAt;
    String category;
    String productType;
    List<ImageResponse> images;
    List<String> cartDetails;
    List<String> favoriteProducts;
    List<String> orderDetails;
}
