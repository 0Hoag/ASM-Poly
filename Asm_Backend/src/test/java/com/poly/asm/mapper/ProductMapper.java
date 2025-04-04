package com.poly.asm.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.poly.asm.dto.response.product.ProductResponse;
import com.poly.asm.entity.FavoriteProduct;
import com.poly.asm.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "favoriteProducts", target = "favoriteProducts", qualifiedByName = "mapFavoriteProducts")
    ProductResponse toProductResponse(Product product);

	@Named("mapFavoriteProducts")
    default List<String> mapFavoriteProducts(List<FavoriteProduct> favoriteProducts) {
        if (favoriteProducts == null ){
        	return null; 
        }
        return favoriteProducts.stream()
        		.map(favoriteProduct -> String.valueOf(favoriteProduct.getProduct().getId()))
        		.collect(Collectors.toList());

    }
}
