package com.poly.asm.service;

import java.util.List;
import java.util.Optional;

import com.poly.asm.dto.request.user.UserRequest;
import com.poly.asm.dto.response.user.ListUserResponse;
import com.poly.asm.dto.response.user.UserResponse;
import com.poly.asm.entity.User;

public interface UserService {
	User createUser(UserRequest request);
	User updateUser(int user,UserRequest request);
	User updateProfile(int user,UserRequest request);
	User changePasword(int userId,String oldPass,String newPass,String comfirmPass);
	List<UserResponse> searchUser(String key);
	Optional<UserResponse> getUser(int id);
	List<ListUserResponse> findAll();
	void deleteUser(int id);
	
}
