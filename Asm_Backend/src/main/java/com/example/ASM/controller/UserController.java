package com.example.ASM.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.request.User.ForgotPasswordRequest;
import com.example.ASM.dto.request.User.UserLoginRequest;
import com.example.ASM.dto.request.User.UserRegisterRequest;
import com.example.ASM.dto.response.ForgotPasswordResponse;
import com.example.ASM.dto.response.UserResponse;
import com.example.ASM.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@RequestBody @Valid UserRegisterRequest request) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.register(request))
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<UserResponse> login(@RequestBody @Valid UserLoginRequest request) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.login(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable("id") int id) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.getUserById(id))
                .build();
    }

    @GetMapping("/email/{email}")
    public ApiResponse<UserResponse> getUserByEmail(@PathVariable("email") String email) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.getUserByEmail(email))
                .build();
    }

    @PostMapping("/forgot-password")
    public ApiResponse<ForgotPasswordResponse> forgotPassword(@RequestBody @Valid ForgotPasswordRequest request) {
        return ApiResponse.<ForgotPasswordResponse>builder()
                .code(1000)
                .result(userService.forgotPassword(request))
                .build();
    }
}