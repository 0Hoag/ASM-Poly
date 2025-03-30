package com.example.ASM.mapper;

import com.example.ASM.dto.request.Cart.CartRequest;
import com.example.ASM.dto.response.CartResponse;
import com.example.ASM.entity.Cart;
import com.example.ASM.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "user", source = "user", qualifiedByName = "mapUser")
    Cart toCart(CartRequest request);

    @Mapping(target = "userName", source = "user", qualifiedByName = "mapUserName")
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
}

