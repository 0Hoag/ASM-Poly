package com.poly.asm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.asm.dto.ApiResponse;
import com.poly.asm.dto.request.address.AddressRequest;
import com.poly.asm.dto.request.user.UpdateUserRequest;
import com.poly.asm.dto.request.user.UserRequest;
import com.poly.asm.dto.response.address.AddressResponse;
import com.poly.asm.dto.response.order.OrderResponse;
import com.poly.asm.dto.response.product.ProductResponse;
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
	public ApiResponse<UserResponse> getProfile(@PathVariable int userId) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.profile(userId))
                .build();
    }
	@PostMapping("/create")
    public ApiResponse<Boolean> createUser(@RequestBody @Valid UserRequest request) {
		return ApiResponse.<Boolean>builder()
                .code(1000)
                .result(userService.createUser(request))
                .build();
	}
	@PostMapping("/update/{userId}")
    public ApiResponse<UserResponse> UpdateProfile(@PathVariable int userId,@RequestBody @Valid UpdateUserRequest request) {
    	return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.updateProfile(userId,request))
                .build();
    }
	@PostMapping("/changePass/{userId}")
	public ApiResponse<UserResponse> changePassword(@PathVariable int userId,@RequestParam String oldPass,@RequestParam String newPass,@RequestParam String confirmPass) {
		return ApiResponse.<UserResponse>builder()
				.code(1000)
				.result(userService.changePassword(userId,oldPass,newPass,confirmPass))
				.build();
	}
	
	@GetMapping("/favorite/{userId}")
	public ApiResponse<List<ProductResponse>> Favorite(@PathVariable int userId) {
		return ApiResponse.<List<ProductResponse>>builder()
				.code(1000)
				.result(favoriteService.getFavoriteUser(userId))
				.build();
	}
	@GetMapping("/order/{userId}")
    public ApiResponse<List<OrderResponse>> HistoryOder(@PathVariable int userId) {
        return ApiResponse.<List<OrderResponse>>builder()
				.code(1000)
				.result(orderService.getOrdersByUserId(userId))
				.build();
    }
	@GetMapping("/address/{userId}")
    public ApiResponse<List<AddressResponse>> Address(@PathVariable int userId) {
    	return ApiResponse.<List<AddressResponse>>builder()
				.code(1000)
				.result(addressService.getAddressByUserId(userId))
				.build();
    }
	@PostMapping("/address/default/{addressId}")
	public ApiResponse<AddressResponse> setDefault(@PathVariable int addressId) {
		return ApiResponse.<AddressResponse>builder()
				.code(1000)
				.result(addressService.setDefault(addressId))
				.build();
	}
	@PostMapping("/address/update/{addressId}")
    public ApiResponse<AddressResponse> updateAddress(@PathVariable int addressId, @RequestBody @Valid AddressRequest request) {
    	return ApiResponse.<AddressResponse>builder()
				.code(1000)
				.result(addressService.updateAddressById(addressId, request))
				.build();
    }
	@PostMapping("/address/create/{userId}")
    public ApiResponse<AddressResponse> createAddress(@PathVariable int userId,@RequestBody @Valid AddressRequest request) {
		return ApiResponse.<AddressResponse>builder()
                .code(1000)
                .result(addressService.createAddressByUserId(userId,request))
                .build();
	}
	@DeleteMapping("/address/delete/{addressId}")
	public ApiResponse<Void> createAddress(@PathVariable int addressId) {
		addressService.deleteAddress(addressId);
		return ApiResponse.<Void>builder()
				.code(1000)
        		.message("Delete success!")
        		.build();
	}
}
