package com.example.ASM.mapper;

import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationRequest;
import com.example.ASM.dto.response.ProductSpecificationResponse;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationUpdateRequest;
import com.example.ASM.entity.ProductSpecification;
import com.example.ASM.entity.SpecificationType;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductSpecificationMapper {
    ProductSpecificationMapper INSTANCE = Mappers.getMapper(ProductSpecificationMapper.class);

    @Mapping(source = "specificationTypeId", target = "specificationType", qualifiedByName = "mapSpecificationType")
    ProductSpecification toProductSpecification(ProductSpecificationRequest request);

    @Mapping(source = "specificationType.specName", target = "specificationTypeName")
    ProductSpecificationResponse toProductSpecificationResponse(ProductSpecification entity);

    @Mapping(target = "id", ignore = true)
    void updateProductSpecification(@MappingTarget ProductSpecification entity, ProductSpecificationUpdateRequest request);

    @Named("mapSpecificationType")
    default SpecificationType mapSpecificationType(Integer id) {
        if (id == null) {
            return null;
        }
        SpecificationType type = new SpecificationType();
        type.setId(id);
        return type;
    }
}
