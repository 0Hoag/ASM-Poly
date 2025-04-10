package com.poly.asm.mapper;

import com.poly.asm.dto.request.Category.CategoryRequest;
import com.poly.asm.dto.request.Category.CategoryUpdateRequest;
import com.poly.asm.dto.response.CategoryResponse;
import com.poly.asm.entity.Category;
import com.poly.asm.entity.Product;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;

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
        return (entity != null && entity.getParentCategory() != null) ? entity.getParentCategory().getId() : 0;
    }

    @Named("mapProductIds")
    default List<Integer> mapProductIds(List<Product> products) {
        return products != null ? products.stream().map(Product::getId).toList() : Collections.emptyList();
    }
}
