package com.example.ASM.mapper;

import com.example.ASM.dto.request.SpecificationType.SpecificationTypeRequest;
import com.example.ASM.dto.request.SpecificationType.SpecificationTypeUpdateRequest;
import com.example.ASM.dto.response.product.SpecificationTypeResponse;
import com.example.ASM.entity.ProductType;
import com.example.ASM.entity.SpecificationType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class SpecificationTypeMapperImpl implements SpecificationTypeMapper {

    @Override
    public SpecificationType toSpecificationType(SpecificationTypeRequest request) {
        if ( request == null ) {
            return null;
        }

        SpecificationType specificationType = new SpecificationType();

        specificationType.setProductType( mapProductType( request.getProductType() ) );
        specificationType.setSpecName( request.getSpecName() );

        return specificationType;
    }

    @Override
    public SpecificationTypeResponse toSpecificationTypeResponse(SpecificationType entity) {
        if ( entity == null ) {
            return null;
        }

        SpecificationTypeResponse.SpecificationTypeResponseBuilder specificationTypeResponse = SpecificationTypeResponse.builder();

        specificationTypeResponse.productTypeName( entityProductTypeNameType( entity ) );
        specificationTypeResponse.productSpecifications( mapProductSpecifications( entity.getProductSpecifications() ) );
        specificationTypeResponse.id( entity.getId() );
        specificationTypeResponse.specName( entity.getSpecName() );

        return specificationTypeResponse.build();
    }

    @Override
    public void updateSpecificationType(SpecificationType entity, SpecificationTypeUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        entity.setProductType( mapProductType( request.getProductType() ) );
        entity.setSpecName( request.getSpecName() );
    }

    private String entityProductTypeNameType(SpecificationType specificationType) {
        if ( specificationType == null ) {
            return null;
        }
        ProductType productType = specificationType.getProductType();
        if ( productType == null ) {
            return null;
        }
        String nameType = productType.getNameType();
        if ( nameType == null ) {
            return null;
        }
        return nameType;
    }
}
