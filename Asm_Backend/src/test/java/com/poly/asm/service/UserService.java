package com.poly.asm.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm.dto.request.user.UpdateUserRequest;
import com.poly.asm.dto.request.user.UserRequest;
import com.poly.asm.dto.response.user.UserResponse;
import com.poly.asm.entity.User;
import com.poly.asm.exception.AppException;
import com.poly.asm.exception.ErrorCode;
import com.poly.asm.mapper.UserMapper;
import com.poly.asm.repository.UserReponsitory;


@Service
public class UserService{
	@Autowired
	UserReponsitory userReponsitory;
	@Autowired
	UserMapper userMapper;
	
	public Boolean createUser(UserRequest request) {
		if ((request.getEmail() == null || request.getEmail().trim().isEmpty()) &&
				(request.getPhoneNumber() == null || request.getPhoneNumber().trim().isEmpty())) {
			throw new AppException(ErrorCode.EMAIL_OR_PHONE_REQUIRED); 
		}
		if (userReponsitory.existsByEmailOrPhoneNumber(request.getEmail(), request.getPhoneNumber())) {
            throw new AppException(ErrorCode.USER_EXITSTED);
        }
        User user = userMapper.toUser(request);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userReponsitory.save(user);
        return true;
	}
	
	public User updateUser(int userId, UserRequest request) {
        User user = userReponsitory.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(user, request);
        return userReponsitory.save(user);
    }
	
	public List<UserResponse> searchUser(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
	        throw new AppException(ErrorCode.MISSING_INPUT); 
	    }

	    List<User> users = userReponsitory.findByFullNameContainingOrEmailOrPhoneNumber(keyword, keyword, keyword);
	    return users.stream()
	            .map(userMapper::toUserResponse)
	            .collect(Collectors.toList());
	}
	
	public UserResponse profile(int userId) {
		User user = userReponsitory.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
		return userMapper.toUserResponse(user);
		
	}
	
	public UserResponse getUser(int id) {
	    User user = userReponsitory.findById(id)
	            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));  
	    return userMapper.toUserResponse(user);
	}

	public List<UserResponse> findAll() {
		List<User> users = userReponsitory.findAll();
		return users.stream()
	            .map(userMapper::toUserResponse)
	            .collect(Collectors.toList());
	}

	public void deleteUser(int id) {
		if (!userReponsitory.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
		userReponsitory.deleteById(id);
	}

	public UserResponse updateProfile(int userId, UpdateUserRequest request) {
		User user = userReponsitory.findById(userId)
				.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
		userMapper.updateProfile(user, request);
		return userMapper.toUserResponse(userReponsitory.save(user)) ;
	}
	
	public UserResponse changePassword(int userId, String oldPass, String newPass, String confirmPass) {
        User user = userReponsitory.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (!oldPass.equals(user.getPassword())) {
            throw new AppException(ErrorCode.PASSWORD_INCORRECT);
        }

        if (!newPass.equals(confirmPass)) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }
        user.setPassword(newPass);
        return userMapper.toUserResponse(userReponsitory.save(user)) ;
    }
	


}
