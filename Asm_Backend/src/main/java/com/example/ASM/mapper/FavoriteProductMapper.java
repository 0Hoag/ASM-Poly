package com.example.ASM.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.example.ASM.dto.response.user.FavoriteProductResponse;
import com.example.ASM.entity.FavoriteProduct;
import com.example.ASM.entity.Product;

@Mapper(componentModel = "spring")
public interface FavoriteProductMapper {

    @Mapping(source = "product", target = "product", qualifiedByName = "mapProductToString")
    FavoriteProductResponse toFavoriteResponse(FavoriteProduct favoriteProduct);

    @Named("mapProductToString")
    default Integer mapProductToString(Product product) {
        return product != null ? product.getId() : null;
    }
}
