package com.example.ASM.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductTypeResponse {
    int id;
    String nameType;
    List<String> products;
    List<String> specificationTypes;
}
