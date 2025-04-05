package com.example.ASM.mapper;

import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationRequest;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationUpdateRequest;
import com.example.ASM.dto.response.product.ProductSpecificationResponse;
import com.example.ASM.entity.ProductSpecification;
import com.example.ASM.entity.SpecificationType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductSpecificationMapper {
    @Mapping(source = "specificationTypeId", target = "specificationType", qualifiedByName = "mapSpecificationType")
    ProductSpecification toProductSpecification(ProductSpecificationRequest request);

    @Mapping(source = "specificationType.id", target = "specificationTypeName")
    ProductSpecificationResponse toProductSpecificationResponse(ProductSpecification entity);

    @Mapping(source = "specificationTypeId", target = "specificationType", qualifiedByName = "mapSpecificationType")
    void updateProductSpecification(@MappingTarget ProductSpecification entity, ProductSpecificationUpdateRequest request);

    @Named("mapSpecificationType")
    default SpecificationType mapSpecificationType(Integer specificationTypeId) {
        if (specificationTypeId == null) {
            return null;
        }
        SpecificationType specificationType = new SpecificationType();
        specificationType.setId(specificationTypeId);
        return specificationType;
    }

    default List<String> mapSpecificationTypeNames(List<ProductSpecification> specifications) {
        return specifications.stream()
                .map(ProductSpecification::getName)
                .collect(Collectors.toList());
    }
}
