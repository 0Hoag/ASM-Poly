package com.example.ASM.mapper;

import com.example.ASM.dto.request.CartDetail.CartDetailRequest;
import com.example.ASM.dto.response.CartDetailResponse;
import com.example.ASM.entity.CartDetail;
import com.example.ASM.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CartDetailMapper {

    @Mapping(target = "product", source = "product", qualifiedByName = "mapProduct")
//    @Mapping(target = "cart", source = "cart", qualifiedByName = "mapCart")
    CartDetail toCartDetail(CartDetailRequest request);

    @Mapping(target = "productName", source = "product", qualifiedByName = "mapProductName")
    CartDetailResponse toCartDetailResponse(CartDetail entity);

    @Named("mapProduct")
    default Product mapProduct(Integer productId) {
        if (productId == null) return null;
        Product product = new Product();
        product.setId(productId);
        return product;
    }

//    @Named("mapCart")
//    default Cart mapCart(Integer cartId) {
//        if (cartId == null) return null;
//        Cart cart = new Cart();
//        cart.setId(cartId);
//        return cart;
//    }

    @Named("mapProductName")
    default String mapProductName(Product product) {
        return (product != null) ? product.getProductName() : null;
    }
}
