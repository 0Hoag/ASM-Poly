package com.example.ASM.dto.response.product;

import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecificationTypeResponse {
    int id;
    String specName;
    List<String> productSpecifications;
    String productTypeName; // productType
}
