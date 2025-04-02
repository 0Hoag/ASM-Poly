package com.poly.asm.mapper;


import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.poly.asm.dto.response.favorite.FavoriteResponse;
import com.poly.asm.entity.FavoriteProduct;
import com.poly.asm.entity.Image;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {
	
    FavoriteResponse toFavoriteResponse(FavoriteProduct favoriteProduct);
    default List<String> map(List<Image> images) {
        if (images == null) {
            return null;
        }
        return images.stream()
                .map(image -> image.getUrl())  
                .collect(Collectors.toList());
    }
}