package com.poly.asm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import com.poly.asm.dto.response.product.ProductResponse;
import com.poly.asm.entity.Product;
import com.poly.asm.entity.Image;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "images", target = "images", qualifiedByName = "mapImages")
    ProductResponse toProductResponse(Product product);

    @Named("mapImages")
    static List<String> mapImages(List<Image> images) {
        return (images != null && !images.isEmpty()) 
            ? images.stream().map(Image::getUrl).collect(Collectors.toList()) 
            : List.of();
    }
}
