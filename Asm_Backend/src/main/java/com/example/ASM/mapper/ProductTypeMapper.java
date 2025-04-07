package com.example.ASM.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.example.ASM.dto.request.ProductType.ProductTypeRequest;
import com.example.ASM.dto.response.product.ProductTypeResponse;
import com.example.ASM.entity.Product;
import com.example.ASM.entity.ProductType;
import com.example.ASM.entity.SpecificationType;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper {

    ProductType toProductType(ProductTypeRequest request);

    @Mapping(target = "products", source = "products", qualifiedByName = "mapProductsToNames")
    @Mapping(
            target = "specificationTypes",
            source = "specificationTypes",
            qualifiedByName = "mapSpecificationTypesToNames")
    ProductTypeResponse toProductTypeResponse(ProductType productType);

    @Named("mapProductsToNames")
    default List<String> mapProductsToNames(List<Product> products) {
        if (products == null) return Collections.emptyList();
        return products.stream().map(Product::getProductName).collect(Collectors.toList());
    }

    @Named("mapSpecificationTypesToNames")
    default List<String> mapSpecificationTypesToNames(List<SpecificationType> specificationTypes) {
        if (specificationTypes == null) return Collections.emptyList();
        return specificationTypes.stream().map(SpecificationType::getSpecName).collect(Collectors.toList());
    }
}
