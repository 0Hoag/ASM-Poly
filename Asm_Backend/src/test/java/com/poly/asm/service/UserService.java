package com.poly.asm.service;

import java.sql.Timestamp;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.asm.dto.PageResponse;
import com.poly.asm.dto.request.user.PasswordRequest;
import com.poly.asm.dto.request.user.UpdateUserRequest;
import com.poly.asm.dto.request.user.UserRequest;
import com.poly.asm.dto.response.user.UserResponse;
import com.poly.asm.exception.AppException;
import com.poly.asm.exception.ErrorCode;
import com.poly.asm.mapper.UserMapper;
import com.poly.asm.repository.UserReponsitory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService{
	UserReponsitory userReponsitory;
	UserMapper userMapper;
	
	public PageResponse<UserResponse> Get(int page , int size) {
		Pageable pageable = PageRequest.of(page -1, size);
		
		var pageData = userReponsitory.findAll(pageable);
		
		var data = pageData.getContent().stream()
				.map(userMapper::toUserResponse)
				.collect(Collectors.toList());
		
		return PageResponse.<UserResponse>builder()
				.currentPage(page)
				.totalPages(pageData.getTotalPages())
				.pageSize(pageData.getSize())
				.totalElements(pageData.getTotalElements())
				.data(data)
                .build();
	}
	
	public PageResponse<UserResponse> Search(String keyword, int page, int size) {
		if (keyword == null || keyword.trim().isEmpty()) {
	        throw new AppException(ErrorCode.MISSING_INPUT); 
	    }
		
		Pageable pageable = PageRequest.of(page -1,size);
		
	    var pageData = userReponsitory.findByFullNameContainingOrEmailOrPhoneNumber(keyword, keyword, keyword, pageable);
	    var data = pageData.getContent().stream()
				.map(userMapper::toUserResponse)
				.collect(Collectors.toList());
		
		return PageResponse.<UserResponse>builder()
				.currentPage(page)
				.totalPages(pageData.getTotalPages())
				.pageSize(pageData.getSize())
				.totalElements(pageData.getTotalElements())
				.data(data)
                .build();
	}
	
	public UserResponse findUserId(int id) {
	    var user = userReponsitory.findById(id)
	            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));  
	    return userMapper.toUserResponse(user);
	}
	
	public Boolean Create(UserRequest request) {
		if ((request.getEmail() == null || request.getEmail().trim().isEmpty()) &&
				(request.getPhoneNumber() == null || request.getPhoneNumber().trim().isEmpty())) {
			throw new AppException(ErrorCode.EMAIL_OR_PHONE_REQUIRED); 
		}
		if (userReponsitory.existsByEmailOrPhoneNumber(request.getEmail(), request.getPhoneNumber())) {
            throw new AppException(ErrorCode.USER_EXITSTED);
        }
        var user = userMapper.toUser(request);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userReponsitory.save(user);
        return true;
	}
	
	public UserResponse Update(int userId, UpdateUserRequest request) {
        var user = userReponsitory.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        
        userMapper.updateUser(user, request);
        
        return userMapper.toUserResponse(userReponsitory.save(user));
    }
	
	public void Delete(int id) {
		if (!userReponsitory.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
		userReponsitory.deleteById(id);
	}
	
	public UserResponse changePassword(int userId, PasswordRequest request) {
        var user = userReponsitory.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (!request.getPassword().equals(user.getPassword())) {
            throw new AppException(ErrorCode.PASSWORD_INCORRECT);
        }

        if (!request.getNewPass().equals(request.getCofimPass())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }
        
        user.setPassword(request.getNewPass());
        return userMapper.toUserResponse(userReponsitory.save(user)) ;
    }
	


}
