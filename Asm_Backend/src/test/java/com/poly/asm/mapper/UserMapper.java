package com.poly.asm.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.poly.asm.dto.request.user.UserRequest;
import com.poly.asm.dto.response.user.UserResponse;
import com.poly.asm.entity.User;

@Mapper(componentModel = "spring", uses = {FavoriteMapper.class})

public interface UserMapper {
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "carts", ignore = true)
    @Mapping(target = "favoriteProducts", ignore = true)
    @Mapping(target = "orders", ignore = true)
    User toUser(UserRequest request);

	@Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "carts", ignore = true)
    @Mapping(target = "favoriteProducts", ignore = true)
    @Mapping(target = "orders", ignore = true)
    void updateUser(@MappingTarget User user, UserRequest request);


	@Mapping(source = "favoriteProducts", target = "favorites", qualifiedByName = "mapFavorites")
    UserResponse toUserResponse(User user);
}
