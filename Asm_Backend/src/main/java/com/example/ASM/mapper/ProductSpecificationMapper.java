package com.example.ASM.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationRequest;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationUpdateRequest;
import com.example.ASM.dto.response.product.ProductSpecificationResponse;
import com.example.ASM.entity.ProductSpecification;
import com.example.ASM.entity.SpecificationType;

@Mapper(componentModel = "spring")
public interface ProductSpecificationMapper {

    @Mapping(target = "specificationType", source = "specificationTypeId", qualifiedByName = "mapSpecificationType")
    ProductSpecification toProductSpecification(ProductSpecificationRequest request);

    @Mapping(target = "specificationType", source = "specificationTypeId", qualifiedByName = "mapSpecificationType")
    void updateProductSpecification(
            @MappingTarget ProductSpecification entity, ProductSpecificationUpdateRequest request);

    @Mapping(target = "specificationTypeName", source = "specificationType.specName")
    ProductSpecificationResponse toProductSpecificationResponse(ProductSpecification entity);

    @Named("mapSpecificationType")
    default SpecificationType mapSpecificationType(Integer specificationTypeId) {
        if (specificationTypeId == null) {
            return null;
        }
        SpecificationType specificationType = new SpecificationType();
        specificationType.setId(specificationTypeId);
        return specificationType;
    }

    @Named("mapProductSpecifications")
    default List<String> mapProductSpecifications(List<ProductSpecification> specifications) {
        if (specifications == null) {
            return Collections.emptyList();
        }
        return specifications.stream().map(ProductSpecification::getName).collect(Collectors.toList());
    }
}
