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
import com.poly.asm.dto.request.address.AddressRequest;
import com.poly.asm.dto.request.user.PasswordRequest;
import com.poly.asm.dto.request.user.UpdateUserRequest;
import com.poly.asm.dto.response.address.AddressResponse;
import com.poly.asm.dto.response.favorite.FavoriteResponse;
import com.poly.asm.dto.response.order.OrderResponse;
import com.poly.asm.dto.response.user.UserResponse;
import com.poly.asm.service.AddressService;
import com.poly.asm.service.FavoriteService;
import com.poly.asm.service.OrderService;
import com.poly.asm.service.UserService;

import jakarta.validation.Valid;
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
	FavoriteService favoriteService;
	OrderService orderService;
	AddressService addressService;
	
	@GetMapping("/{userId}")
	public ApiResponse<UserResponse> findUserId(@PathVariable int userId) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.findUserId(userId))
                .build();
    }
	
	@PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable int userId,@RequestBody UpdateUserRequest request) {
    	return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.Update(userId,request))
                .build();
    }
	@PutMapping("/changePass/{userId}")
	public ApiResponse<UserResponse> changePassword(@PathVariable int userId,@RequestBody PasswordRequest request) {
		return ApiResponse.<UserResponse>builder()
				.code(1000)
				.result(userService.changePassword(userId,request))
				.build();
	}
	
    @GetMapping("/address/{userId}")
    public ApiResponse<PageResponse<AddressResponse>> getAddress(@PathVariable int userId,
    		@RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
    	return ApiResponse.<PageResponse<AddressResponse>>builder()
				.code(1000)
				.result(addressService.getUserId(userId,page,size))
				.build();
    }
	@PostMapping("/address/{userId}")
    public ApiResponse<AddressResponse> createAddress(@PathVariable int userId,@RequestBody AddressRequest request) {
		return ApiResponse.<AddressResponse>builder()
                .code(1000)
                .result(addressService.createByUserId(userId,request))
                .build();
	}
    
	@GetMapping("/like")
	public ApiResponse<String> toggleLike(@RequestParam int userId, @RequestParam int productId) {
		return ApiResponse.<String>builder()
				.code(1000)
				.result(favoriteService.toggleLike( userId, productId))
				.build();
	}
	
	@GetMapping("/favorite/{userId}")
	public ApiResponse<PageResponse<FavoriteResponse>> Favorite(@PathVariable int userId,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.<PageResponse<FavoriteResponse>>builder()
				.code(1000)
				.result(favoriteService.getFavoriteUser(userId, page, size))
				.build();
	}
	@GetMapping("/order/{userId}")
    public ApiResponse<PageResponse<OrderResponse>> HistoryOder(@PathVariable int userId,
    		@RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<OrderResponse>>builder()
				.code(1000)
				.result(orderService.getOrdersByUserId(userId, page, size))
				.build();
    }
	
}
