package com.example.ASM.mapper;

import com.example.ASM.dto.request.SpecificationTypeRequest;
import com.example.ASM.dto.request.SpecificationTypeUpdateRequest;
import com.example.ASM.dto.response.SpecificationTypeResponse;
import com.example.ASM.entity.ProductSpecification;
import com.example.ASM.entity.ProductType;
import com.example.ASM.entity.SpecificationType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SpecificationTypeMapper {

    @Mapping(target = "productType", source = "productType", qualifiedByName = "mapProductType")
    SpecificationType toSpecificationType(SpecificationTypeRequest request);

    @Mapping(target = "productTypeName", source = "productType.nameType") // Lấy tên của ProductType
    @Mapping(target = "productSpecifications", source = "productSpecifications", qualifiedByName = "mapProductSpecifications")
    SpecificationTypeResponse toSpecificationTypeResponse(SpecificationType entity);

    @Mapping(target = "productType", source = "productType", qualifiedByName = "mapProductType")
    void updateSpecificationType(@MappingTarget SpecificationType entity, SpecificationTypeUpdateRequest request);

    @Named("mapProductType")
    default ProductType mapProductType(Integer productTypeId) {
        if (productTypeId == null) {
            return null;
        }
        ProductType productType = new ProductType();
        productType.setId(productTypeId);
        return productType;
    }

    @Named("mapProductSpecifications")
    default List<String> mapProductSpecifications(List<ProductSpecification> specifications) {
        if (specifications == null) {
            return Collections.emptyList();
        }
        return specifications.stream()
                .map(ProductSpecification::getName)
                .collect(Collectors.toList());
    }
}
