package com.example.ASM.mapper;

import java.util.Collections;
import java.util.List;

import org.mapstruct.*;

import com.example.ASM.dto.request.Category.CategoryRequest;
import com.example.ASM.dto.request.Category.CategoryUpdateRequest;
import com.example.ASM.dto.response.CategoryResponse;
import com.example.ASM.entity.Category;
import com.example.ASM.entity.Product;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "parentCategory", source = "parentCategory", qualifiedByName = "mapParentCategory")
    Category toCategory(CategoryRequest request);

    @Mapping(target = "parentCategory", source = "parentCategory", qualifiedByName = "mapParentCategoryResponse")
    @Mapping(target = "products", source = "products", qualifiedByName = "mapProductIds")
    CategoryResponse toCategoryResponse(Category entity);

    @Mapping(target = "parentCategory", source = "parentCategory", qualifiedByName = "mapParentCategory")
    void updateCategory(@MappingTarget Category profile, CategoryUpdateRequest request);

    @Named("mapParentCategory")
    default Category mapParentCategory(Integer parentCategoryId) {
        if (parentCategoryId == null || parentCategoryId == 0) return null;
        Category category = new Category();
        category.setId(parentCategoryId);
        return category;
    }

    @Named("mapParentCategoryResponse")
    default int mapParentCategoryResponse(Category entity) {
        return (entity != null && entity.getParentCategory() != null)
                ? entity.getParentCategory().getId()
                : 0;
    }

    @Named("mapProductIds")
    default List<Integer> mapProductIds(List<Product> products) {
        return products != null ? products.stream().map(Product::getId).toList() : Collections.emptyList();
    }
}
