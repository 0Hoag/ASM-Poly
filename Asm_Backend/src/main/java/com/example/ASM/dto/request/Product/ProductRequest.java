package com.example.ASM.dto.request.Product;

import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    String productName;
    String description;
    double price;
    double salePrice;
    int soldQuantity;
    int stockQuantity;
    int category;
    int productType;
    // default null
    List<String> cartDetails;
    List<String> favoriteProducts;
    List<String> images;
    List<String> orderDetails;
}
