package com.example.ASM.mapper;

import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationRequest;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationUpdateRequest;
import com.example.ASM.dto.response.ProductSpecificationResponse;
import com.example.ASM.entity.ProductSpecification;
import com.example.ASM.entity.SpecificationType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class ProductSpecificationMapperImpl implements ProductSpecificationMapper {

    @Override
    public ProductSpecification toProductSpecification(ProductSpecificationRequest request) {
        if ( request == null ) {
            return null;
        }

        ProductSpecification productSpecification = new ProductSpecification();

        productSpecification.setSpecificationType( mapSpecificationType( request.getSpecificationTypeId() ) );
        productSpecification.setName( request.getName() );
        productSpecification.setValue( request.getValue() );

        return productSpecification;
    }

    @Override
    public ProductSpecificationResponse toProductSpecificationResponse(ProductSpecification entity) {
        if ( entity == null ) {
            return null;
        }

        ProductSpecificationResponse.ProductSpecificationResponseBuilder productSpecificationResponse = ProductSpecificationResponse.builder();

        productSpecificationResponse.specificationTypeName( entitySpecificationTypeSpecName( entity ) );
        productSpecificationResponse.id( entity.getId() );
        productSpecificationResponse.name( entity.getName() );
        productSpecificationResponse.value( entity.getValue() );

        return productSpecificationResponse.build();
    }

    @Override
    public void updateProductSpecification(ProductSpecification entity, ProductSpecificationUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        entity.setName( request.getName() );
        entity.setValue( request.getValue() );
    }

    private String entitySpecificationTypeSpecName(ProductSpecification productSpecification) {
        if ( productSpecification == null ) {
            return null;
        }
        SpecificationType specificationType = productSpecification.getSpecificationType();
        if ( specificationType == null ) {
            return null;
        }
        String specName = specificationType.getSpecName();
        if ( specName == null ) {
            return null;
        }
        return specName;
    }
}
