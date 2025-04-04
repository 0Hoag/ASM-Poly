package com.example.ASM.mapper;

import org.mapstruct.*;

import com.example.ASM.dto.request.User.UserRegisterRequest;
import com.example.ASM.dto.response.UserResponse;
import com.example.ASM.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRegisterRequest request);

    UserResponse toUserResponse(User user);
}