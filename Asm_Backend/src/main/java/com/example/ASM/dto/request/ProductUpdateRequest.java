package com.example.ASM.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateRequest {
    String productName;
    String description;
    double price;
    double salePrice;
    int soldQuantity;
    int stockQuantity;
    int category;
    int productType;
}
