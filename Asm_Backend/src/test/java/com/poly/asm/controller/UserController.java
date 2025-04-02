package com.poly.asm.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.asm.dto.ApiResponse;
import com.poly.asm.dto.PageResponse;
import com.poly.asm.dto.request.user.PasswordRequest;
import com.poly.asm.dto.request.user.UpdateUserRequest;
import com.poly.asm.dto.request.user.UserRequest;
import com.poly.asm.dto.response.user.UserResponse;
import com.poly.asm.service.UserService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
	UserService userService;
	
	@GetMapping("/")
	public ApiResponse<PageResponse<UserResponse>> Get(
			@RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.<PageResponse<UserResponse>>builder()
                .code(1000)
                .result(userService.Get(page,size))
                .build();
	}
	@GetMapping("/{userId}")
	public ApiResponse<UserResponse> findId(@PathVariable int userId) {
		return ApiResponse.<UserResponse>builder()
				.code(1000)
				.result(userService.findUserId(userId))
				.build();
	}
	@GetMapping("/search")
	public ApiResponse<PageResponse<UserResponse>> Search( @RequestParam String keyword,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.<PageResponse<UserResponse>>builder()
				.code(1000)
				.result(userService.Search(keyword,page,size))
				.build();
	}
	
	@PostMapping("/create")
    public ApiResponse<Boolean> Create(@RequestBody @Valid UserRequest request) {
		return ApiResponse.<Boolean>builder()
                .code(1000)
                .result(userService.Create(request))
                .build();
	}
	
	@PutMapping("/{userId}")
    public ApiResponse<UserResponse> Update(@PathVariable int userId,@RequestBody @Valid UpdateUserRequest request) {
    	return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.Update(userId,request))
                .build();
    }
	@PostMapping("/changePass/{userId}")
	public ApiResponse<UserResponse> changePassword(@PathVariable int userId,@RequestBody @Valid PasswordRequest request) {
		return ApiResponse.<UserResponse>builder()
				.code(1000)
				.result(userService.changePassword(userId,request))
				.build();
	}
	
}
