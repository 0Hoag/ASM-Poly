package com.example.ASM.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.Address.AddressRequest;
import com.example.ASM.dto.response.user.AddressResponse;
import com.example.ASM.service.AddressService;

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

    @PostMapping("/")
    public ApiResponse<AddressResponse> create(@RequestBody @Valid AddressRequest request) {
        return ApiResponse.<AddressResponse>builder()
                .code(1000)
                .result(addressService.Create(request))
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<PageResponse<AddressResponse>> getAddress(
            @PathVariable int userId,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<AddressResponse>>builder()
                .code(1000)
                .result(addressService.getUserId(userId, page, size))
                .build();
    }

    @GetMapping("/Get")
    public ApiResponse<PageResponse<AddressResponse>> Get(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<AddressResponse>>builder()
                .code(1000)
                .result(addressService.Get(page, size))
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
    public ApiResponse<AddressResponse> update(
            @PathVariable int addressId, @RequestBody @Valid AddressRequest request) {
        return ApiResponse.<AddressResponse>builder()
                .code(1000)
                .result(addressService.Update(addressId, request))
                .build();
    }

    @DeleteMapping("/{addressId}")
    public ApiResponse<Void> delete(@PathVariable int addressId) {
        addressService.Delete(addressId);
        return ApiResponse.<Void>builder().code(1000).message("Delete success!").build();
    }
}
