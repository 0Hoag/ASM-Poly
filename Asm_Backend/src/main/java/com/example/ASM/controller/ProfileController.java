package com.example.ASM.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.User.PasswordRequest;
import com.example.ASM.dto.request.User.UpdateUserRequest;
import com.example.ASM.dto.response.order.OrderResponse;
import com.example.ASM.dto.response.user.FavoriteProductResponse;
import com.example.ASM.dto.response.user.UserResponse;
import com.example.ASM.service.FavoriteProductService;
import com.example.ASM.service.OrderService;
import com.example.ASM.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileController {
    UserService userService;
    FavoriteProductService favoriteService;
    OrderService orderService;

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> findUserId(@PathVariable int userId) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.findUserId(userId))
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(
            @PathVariable int userId, @RequestBody @Valid UpdateUserRequest request) {
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

    @PostMapping("/like")
    public ApiResponse<String> toggleLike(@RequestParam int userId, @RequestParam int prodctId) {
        return ApiResponse.<String>builder()
                .code(1000)
                .result(favoriteService.toggleLike(userId, prodctId))
                .build();
    }

    @GetMapping("/favorite/{userId}")
    public ApiResponse<PageResponse<FavoriteProductResponse>> Favorite(
            @PathVariable int userId,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<FavoriteProductResponse>>builder()
                .code(1000)
                .result(favoriteService.getFavoriteUser(userId, page, size))
                .build();
    }

    @GetMapping("/order/{userId}")
    public ApiResponse<PageResponse<OrderResponse>> HistoryOder(
            @PathVariable int userId,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<OrderResponse>>builder()
                .code(1000)
                .result(orderService.getOrdersByUserId(userId, page, size))
                .build();
    }
}
