package com.example.ASM.controller;


import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.Auth.ForgotPasswordRequest;
import com.example.ASM.dto.request.Auth.UserLoginRequest;
import com.example.ASM.dto.request.User.PasswordRequest;
import com.example.ASM.dto.request.User.UpdateUserRequest;
import com.example.ASM.dto.request.User.UserRequest;
import com.example.ASM.dto.response.auth.ForgotPasswordResponse;
import com.example.ASM.dto.response.user.UserResponse;
import com.example.ASM.service.AuthService;
import com.example.ASM.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
	AuthService authService;
	
	@PostMapping("/login")
	public ApiResponse<UserResponse> Login(@RequestBody UserLoginRequest request) {
		return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(authService.login(request))
                .build();
	}

	@PostMapping("/forgot-password")
	public ApiResponse<ForgotPasswordResponse> ForgotPassword(@RequestBody ForgotPasswordRequest request) {
		return ApiResponse.<ForgotPasswordResponse>builder()
				.code(1000)
				.result(authService.forgotPassword(request))
				.build();
	}
}
