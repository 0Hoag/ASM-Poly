package com.poly.asm.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.poly.asm.dto.response.favorite.FavoriteResponse;
import com.poly.asm.entity.FavoriteProduct;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface FavoriteMapper {
    @Mapping(source = "product", target = "product")
    FavoriteResponse toFavoriteResponse(FavoriteProduct favoriteProduct);

    @Named("mapFavorites") 
    default List<FavoriteResponse> mapFavorites(List<FavoriteProduct> favoriteProducts) {
        if (favoriteProducts == null || favoriteProducts.isEmpty()) {
            return null;
        }
        return favoriteProducts.stream()
                .map(this::toFavoriteResponse)
                .collect(Collectors.toList());
    }
}