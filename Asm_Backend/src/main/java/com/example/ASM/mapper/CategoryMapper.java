package com.example.ASM.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.*;

import com.example.ASM.dto.request.Category.CategoryRequest;
import com.example.ASM.dto.request.Category.CategoryUpdateRequest;
import com.example.ASM.dto.response.product.CategoryResponse;
import com.example.ASM.entity.Category;
import com.example.ASM.entity.Product;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "parentCategory", source = "parentCategory", qualifiedByName = "mapParentCategory")
    Category toCategory(CategoryRequest request);

    @Mapping(
            target = "parentCategory",
            expression = "java(entity.getParentCategory() != null ? entity.getParentCategory().getId() : 0)")
    @Mapping(target = "products", source = "products", qualifiedByName = "mapProductIds")
    @Mapping(target = "subCategories", source = "subCategories", qualifiedByName = "mapSubCategories")
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

    default int mapParentCategoryResponse(Category entity) {
        return (entity != null && entity.getParentCategory() != null)
                ? entity.getParentCategory().getId()
                : 0;
    }

    @Named("mapProductIds")
    default List<String> mapProductIds(List<Product> products) {
        if (products == null) return Collections.emptyList();
        return products.stream()
                .map(product -> String.valueOf(product.getProductName()))
                .collect(Collectors.toList());
    }

    @Named("mapSubCategories")
    default List<CategoryResponse> mapSubCategories(List<Category> subCategories) {
        if (subCategories == null) return Collections.emptyList();
        return subCategories.stream().map(this::toCategoryResponse).collect(Collectors.toList());
    }
}
