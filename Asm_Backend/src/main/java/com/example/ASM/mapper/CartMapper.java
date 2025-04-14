package com.example.ASM.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.example.ASM.dto.request.Cart.CartRequest;
import com.example.ASM.dto.response.cart.CartDetailResponse;
import com.example.ASM.dto.response.cart.CartResponse;
import com.example.ASM.entity.Cart;
import com.example.ASM.entity.CartDetail;
import com.example.ASM.entity.Product;
import com.example.ASM.entity.User;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "user", source = "user", qualifiedByName = "mapUser")
    Cart toCart(CartRequest request);

    @Mapping(target = "userName", source = "user", qualifiedByName = "mapUserName")
    @Mapping(target = "userId", source = "user", qualifiedByName = "mapUserId")
    @Mapping(target = "cartDetails", source = "cartDetails", qualifiedByName = "mapCartDetails")
    CartResponse toCartResponse(Cart entity);

    @Named("mapUser")
    default User mapUser(Integer user) {
        if (user == null) return null;
        User user1 = new User();
        user1.setId(user);
        return user1;
    }

    @Named("mapUserName")
    default String mapUserName(User user) {
        return (user != null) ? user.getFullName() : null;
    }

    @Named("mapUserId")
    default Integer mapUseId(User user) {
        return (user != null) ? user.getId() : null;
    }
    @Named("mapCartDetails")
    default List<CartDetailResponse> mapCartDetails(List<CartDetail> cartDetails) {
        if (cartDetails == null) {
            return null;
        }
        return cartDetails.stream().map(this::mapCartDetail).collect(Collectors.toList());
    }

    @Mapping(target = "cart", source = "cart", qualifiedByName = "mapCartId")
    @Mapping(target = "productName", source = "product", qualifiedByName = "mapProductName")
    CartDetailResponse mapCartDetail(CartDetail cartDetail);

    @Named("mapProductName")
    default String mapProductName(Product product) {
        return (product != null) ? product.getProductName() : null;
    }

    @Named("mapCartId")
    default Integer mapCartId(Cart cart) {
        return cart != null ? cart.getId() : null;
    }
}
