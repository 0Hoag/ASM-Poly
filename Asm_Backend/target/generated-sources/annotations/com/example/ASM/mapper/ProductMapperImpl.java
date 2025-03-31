package com.example.ASM.mapper;

import com.example.ASM.dto.request.Product.ProductRequest;
import com.example.ASM.dto.request.Product.ProductUpdateRequest;
import com.example.ASM.dto.response.ProductResponse;
import com.example.ASM.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product product = new Product();

        product.setDescription( request.getDescription() );
        product.setPrice( request.getPrice() );
        product.setProductName( request.getProductName() );
        product.setSalePrice( request.getSalePrice() );
        product.setSoldQuantity( request.getSoldQuantity() );
        product.setStockQuantity( request.getStockQuantity() );
        product.setCategory( map( request.getCategory() ) );
        product.setProductType( mapProductType( request.getProductType() ) );

        return product;
    }

    @Override
    public ProductResponse toProductResponse(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductResponse.ProductResponseBuilder productResponse = ProductResponse.builder();

        productResponse.id( entity.getId() );
        productResponse.productName( entity.getProductName() );
        productResponse.description( entity.getDescription() );
        productResponse.price( entity.getPrice() );
        productResponse.salePrice( entity.getSalePrice() );
        productResponse.soldQuantity( entity.getSoldQuantity() );
        productResponse.stockQuantity( entity.getStockQuantity() );
        productResponse.createdAt( entity.getCreatedAt() );
        productResponse.category( map( entity.getCategory() ) );
        productResponse.productType( mapProductType( entity.getProductType() ) );

        return productResponse.build();
    }

    @Override
    public void updateProduct(Product entity, ProductUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        entity.setDescription( request.getDescription() );
        entity.setPrice( request.getPrice() );
        entity.setProductName( request.getProductName() );
        entity.setSalePrice( request.getSalePrice() );
        entity.setSoldQuantity( request.getSoldQuantity() );
        entity.setStockQuantity( request.getStockQuantity() );
        entity.setCategory( map( request.getCategory() ) );
        entity.setProductType( mapProductType( request.getProductType() ) );
    }
}
