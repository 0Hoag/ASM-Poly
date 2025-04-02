package com.poly.asm.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.poly.asm.dto.request.user.PasswordRequest;
import com.poly.asm.dto.request.user.UpdateUserRequest;
import com.poly.asm.dto.request.user.UserRequest;
import com.poly.asm.dto.response.user.UserResponse;
import com.poly.asm.entity.User;

@Mapper(componentModel = "spring", uses = {FavoriteMapper.class})

public interface UserMapper {
    User toUser(UserRequest request);

    
    void updateUser(@MappingTarget User user, UpdateUserRequest request);
    
    User toPass(PasswordRequest request);

    UserResponse toUserResponse(User user);
}
