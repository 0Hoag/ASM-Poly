package com.example.ASM.dto.response.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSpecificationResponse {
    private int id;
    private String name;
    private String value;
    private String specificationTypeName; // specification
    private String productID;
}
