package com.example.ASM.dto.response;

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
    String productTypeName; // productType
    List<String> productSpecifications;
}
