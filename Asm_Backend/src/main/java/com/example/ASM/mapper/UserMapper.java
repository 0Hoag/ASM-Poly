package com.example.ASM.mapper;

import org.mapstruct.*;

import com.example.ASM.dto.request.User.UserRegisterRequest;
import com.example.ASM.dto.response.UserResponse;
import com.example.ASM.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "carts", ignore = true)
    @Mapping(target = "favoriteProducts", ignore = true)
    @Mapping(target = "orders", ignore = true)
    User toUser(UserRegisterRequest request);

    UserResponse toUserResponse(User user);
}