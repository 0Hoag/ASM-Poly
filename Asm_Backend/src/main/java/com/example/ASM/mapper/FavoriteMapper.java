package com.example.ASM.mapper;


import com.example.ASM.dto.response.user.FavoriteResponse;
import com.example.ASM.entity.FavoriteProduct;
import com.example.ASM.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {

    @Mapping(source = "product", target = "product", qualifiedByName = "mapProductToString")
    FavoriteResponse toFavoriteResponse(FavoriteProduct favoriteProduct);

    @Named("mapProductToString")
    default Integer mapProductToString(Product product) {
        return product != null ? product.getId() : null;
    }
}
