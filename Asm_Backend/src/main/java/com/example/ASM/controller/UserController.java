package com.example.ASM.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.User.PasswordRequest;
import com.example.ASM.dto.request.User.UpdateUserRequest;
import com.example.ASM.dto.request.User.UserRequest;
import com.example.ASM.dto.response.user.UserResponse;
import com.example.ASM.service.UserService;

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

    @GetMapping("/List")
    public ApiResponse<List<UserResponse>> List() {
        return ApiResponse.<List<UserResponse>>builder()
                .code(1000)
                .result(userService.List())
                .build();
    }

    @GetMapping("/Get")
    public ApiResponse<PageResponse<UserResponse>> Get(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<UserResponse>>builder()
                .code(1000)
                .result(userService.Get(page, size))
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
    public ApiResponse<PageResponse<UserResponse>> Search(
            @RequestParam String keyword,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<UserResponse>>builder()
                .code(1000)
                .result(userService.Search(keyword, page, size))
                .build();
    }

    @PostMapping("/")
    public ApiResponse<Boolean> Create(@RequestBody @Valid UserRequest request) {
        return ApiResponse.<Boolean>builder()
                .code(1000)
                .result(userService.Create(request))
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> Update(@PathVariable int userId, @RequestBody @Valid UpdateUserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.Update(userId, request))
                .build();
    }

    @PostMapping("/changePass/{userId}")
    public ApiResponse<UserResponse> changePassword(
            @PathVariable int userId, @RequestBody @Valid PasswordRequest request) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.changePassword(userId, request))
                .build();
    }
}
