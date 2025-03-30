package com.example.ASM.mapper;

import com.example.ASM.dto.request.Product.ProductRequest;
import com.example.ASM.dto.request.Product.ProductUpdateRequest;
import com.example.ASM.dto.response.ProductResponse;
import com.example.ASM.entity.Category;
import com.example.ASM.entity.Product;
import com.example.ASM.entity.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductRequest request);

    ProductResponse toProductResponse(Product entity);

    void updateProduct(@MappingTarget Product entity, ProductUpdateRequest request);

    default Category map(int categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }

    default int map(Category category) {
        return category != null ? category.getId() : 0;
    }

    default ProductType mapProductType(int productTypeId) {
        ProductType productType = new ProductType();
        productType.setId(productTypeId);
        return productType;
    }

    default int mapProductType(ProductType productType) {
        return productType != null ? productType.getId() : 0;
    }
}
