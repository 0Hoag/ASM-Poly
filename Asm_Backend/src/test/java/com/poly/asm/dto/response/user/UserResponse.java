package com.poly.asm.dto.response.user;

import java.util.List;

import com.poly.asm.dto.response.favorite.FavoriteResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
	int id;
	String fullName;
	String password;
	String phoneNumber;
	String email;
	boolean role;
	List<FavoriteResponse> favorites;
	
}
