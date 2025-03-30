package com.example.ASM.mapper;

import com.example.ASM.dto.request.Category.CategoryRequest;
import com.example.ASM.dto.request.Category.CategoryUpdateRequest;
import com.example.ASM.dto.response.CategoryResponse;
import com.example.ASM.entity.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
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

        categoryResponse.parentCategory( mapParentCategoryResponse( entity.getParentCategory() ) );
        categoryResponse.products( mapProductIds( entity.getProducts() ) );
        categoryResponse.id( entity.getId() );
        categoryResponse.categoryName( entity.getCategoryName() );
        categoryResponse.subCategories( categoryListToCategoryResponseList( entity.getSubCategories() ) );

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

    protected List<CategoryResponse> categoryListToCategoryResponseList(List<Category> list) {
        if ( list == null ) {
            return null;
        }

        List<CategoryResponse> list1 = new ArrayList<CategoryResponse>( list.size() );
        for ( Category category : list ) {
            list1.add( toCategoryResponse( category ) );
        }

        return list1;
    }
}
