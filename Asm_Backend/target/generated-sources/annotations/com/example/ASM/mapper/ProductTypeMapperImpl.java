package com.example.ASM.mapper;

import com.example.ASM.dto.request.ProductType.ProductTypeRequest;
import com.example.ASM.dto.response.ProductTypeResponse;
import com.example.ASM.entity.ProductType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ProductTypeMapperImpl implements ProductTypeMapper {

    @Override
    public ProductType toProductType(ProductTypeRequest request) {
        if ( request == null ) {
            return null;
        }

        ProductType productType = new ProductType();

        productType.setNameType( request.getNameType() );

        return productType;
    }

    @Override
    public ProductTypeResponse toProductTypeResponse(ProductType productType) {
        if ( productType == null ) {
            return null;
        }

        ProductTypeResponse.ProductTypeResponseBuilder productTypeResponse = ProductTypeResponse.builder();

        productTypeResponse.products( mapProductsToNames( productType.getProducts() ) );
        productTypeResponse.specificationTypes( mapSpecificationTypesToNames( productType.getSpecificationTypes() ) );
        productTypeResponse.id( productType.getId() );
        productTypeResponse.nameType( productType.getNameType() );

        return productTypeResponse.build();
    }
}
