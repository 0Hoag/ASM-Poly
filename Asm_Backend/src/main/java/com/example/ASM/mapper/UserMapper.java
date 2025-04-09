package com.example.ASM.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.ASM.dto.request.User.PasswordRequest;
import com.example.ASM.dto.request.User.UpdateUserRequest;
import com.example.ASM.dto.request.User.UserRequest;
import com.example.ASM.dto.response.user.UserResponse;
import com.example.ASM.entity.User;

@Mapper(
        componentModel = "spring",
        uses = {FavoriteProductMapper.class})
public interface UserMapper {
    User toUser(UserRequest request);

    void updateUser(@MappingTarget User user, UpdateUserRequest request);

    User toPass(PasswordRequest request);

    UserResponse toUserResponse(User user);
}
