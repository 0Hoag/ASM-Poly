package com.poly.asm.service.Impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm.dto.request.user.UserRequest;
import com.poly.asm.dto.response.user.ListUserResponse;
import com.poly.asm.dto.response.user.UserResponse;
import com.poly.asm.entity.User;
import com.poly.asm.mapper.UserMapper;
import com.poly.asm.repository.UserReponsitory;
import com.poly.asm.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserReponsitory userReponsitory;
	@Autowired
	UserMapper userMapper;
	
	@Override
	public User createUser(UserRequest request) {
		if (userReponsitory.existsByEmailOrPhoneNumber(request.getEmail(),request.getPhoneNumber())) {
			throw new RuntimeException("Email hoặc số điện thoại đã tồn tại!");
		}
		User user = userMapper.toUser(request);
		user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		return userReponsitory.save(user);
	}
	
	@Override
	public User updateUser(int userId, UserRequest request) {
		User user = userReponsitory.findById(userId).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại!"));
	    userMapper.updateUser(user, request);
		return userReponsitory.save(user);
	}
	
	@Override
	public List<UserResponse> searchUser(String keyword) {
		List<User> users = userReponsitory.findByFullNameContainingOrEmailOrPhoneNumber(keyword, keyword, keyword);
	    return users.stream()
	            .map(userMapper::toUserResponse)
	            .collect(Collectors.toList());
	}
	
	@Override
	public Optional<UserResponse> getUser(int id) {
		return userReponsitory.findById(id)
				.map(userMapper::toUserResponse);
	}

	@Override
	public List<ListUserResponse> findAll() {
		List<User> users = userReponsitory.findAll();
		List<UserResponse> userResponses = users.stream()
				.map(userMapper::toUserResponse)
				.collect(Collectors.toList());
		return List.of(new ListUserResponse(userResponses));
	}

	@Override
	public void deleteUser(int id) {
		userReponsitory.deleteById(id);
	}

	@Override
	public User updateProfile(int userId, UserRequest request) {
		User user = userReponsitory.findById(userId).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại!"));
		userMapper.updateUser(user, request);
		return userReponsitory.save(user);
	}
	
	@Override
	public User changePasword(int userId, String oldPass, String newPass, String comfirmPass) {
		User user = userReponsitory.findById(userId)
		        .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại!"));
		if (!oldPass.equals(user.getPassword())) 
			throw new RuntimeException("Mật khẩu cũ không đúng");
		if (!newPass.equals(comfirmPass))
			throw new RuntimeException("Xác nhận mật khẩu mới không khớp");
		user.setPassword(newPass);
		return userReponsitory.save(user);
	}
	


}
