package com.example.ASM.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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
