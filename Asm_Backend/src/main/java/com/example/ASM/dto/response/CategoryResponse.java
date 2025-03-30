package com.example.ASM.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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
    List<Integer> products;
}
