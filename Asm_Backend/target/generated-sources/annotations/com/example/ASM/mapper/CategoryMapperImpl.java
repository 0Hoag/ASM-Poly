package com.example.ASM.mapper;

import com.example.ASM.dto.request.Category.CategoryRequest;
import com.example.ASM.dto.request.Category.CategoryUpdateRequest;
import com.example.ASM.dto.response.product.CategoryResponse;
import com.example.ASM.entity.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toCategory(CategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category category = new Category();

        category.setParentCategory( mapParentCategory( request.getParentCategory() ) );
        category.setCategoryName( request.getCategoryName() );

        return category;
    }

    @Override
    public CategoryResponse toCategoryResponse(Category entity) {
        if ( entity == null ) {
            return null;
        }

        CategoryResponse.CategoryResponseBuilder categoryResponse = CategoryResponse.builder();

        categoryResponse.products( mapProductIds( entity.getProducts() ) );
        categoryResponse.subCategories( mapSubCategories( entity.getSubCategories() ) );
        categoryResponse.id( entity.getId() );
        categoryResponse.categoryName( entity.getCategoryName() );

        categoryResponse.parentCategory( entity.getParentCategory() != null ? entity.getParentCategory().getId() : 0 );

        return categoryResponse.build();
    }

    @Override
    public void updateCategory(Category profile, CategoryUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        profile.setParentCategory( mapParentCategory( request.getParentCategory() ) );
        profile.setCategoryName( request.getCategoryName() );
    }
}
