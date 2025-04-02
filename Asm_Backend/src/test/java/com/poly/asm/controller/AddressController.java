package com.poly.asm.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.poly.asm.dto.response.address.AddressResponse;
import com.poly.asm.service.AddressService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressController {
    AddressService addressService;
    
    @GetMapping("/")
	public ApiResponse<PageResponse<AddressResponse>> Get(
			@RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.<PageResponse<AddressResponse>>builder()
				.code(1000)
				.result(addressService.Get(page,size))
				.build();
	}

	@PostMapping("/default/{addressId}")
	public ApiResponse<AddressResponse> setDefault(@PathVariable int addressId) {
		return ApiResponse.<AddressResponse>builder()
				.code(1000)
				.result(addressService.setDefault(addressId))
				.build();
	}
	@PutMapping("/{addressId}")
    public ApiResponse<AddressResponse> updateAddress(@PathVariable int addressId, @RequestBody @Valid AddressRequest request) {
    	return ApiResponse.<AddressResponse>builder()
				.code(1000)
				.result(addressService.Update(addressId, request))
				.build();
    }
	@DeleteMapping("/delete/{addressId}")
	public ApiResponse<Void> createAddress(@PathVariable int addressId) {
		addressService.Delete(addressId);
		return ApiResponse.<Void>builder()
				.code(1000)
        		.message("Delete success!")
        		.build();
	}
}
