package com.example.ASM.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.example.ASM.dto.request.Cart.CartRequest;
import com.example.ASM.dto.response.CartDetailResponse;
import com.example.ASM.dto.response.CartResponse;
import com.example.ASM.entity.Cart;
import com.example.ASM.entity.CartDetail;
import com.example.ASM.entity.User;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "user", source = "user", qualifiedByName = "mapUser")
    Cart toCart(CartRequest request);

    @Mapping(target = "userName", source = "user", qualifiedByName = "mapUserName")
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

    @Named("mapCartDetails")
    default List<CartDetailResponse> mapCartDetails(List<CartDetail> cartDetails) {
        if (cartDetails == null) {
            return null;
        }
        return cartDetails.stream().map(this::mapCartDetail).collect(Collectors.toList());
    }

    @Mapping(target = "cart", source = "cart", qualifiedByName = "mapCartId")
    CartDetailResponse mapCartDetail(CartDetail cartDetail);

    @Named("mapCartId")
    default Integer mapCartId(Cart cart) {
        return cart != null ? cart.getId() : null;
    }
}
