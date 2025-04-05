package com.example.ASM.dto.response.product;

import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    int id;
    String categoryName;
    int parentCategory;
    List<CategoryResponse> subCategories;
    List<String> products;
}
