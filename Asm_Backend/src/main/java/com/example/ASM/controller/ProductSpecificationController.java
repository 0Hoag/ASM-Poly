package com.example.ASM.controller;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationRequest;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationUpdateRequest;
import com.example.ASM.dto.response.ProductSpecificationResponse;
import com.example.ASM.service.ProductSpecificationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productSpecification")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductSpecificationController {

    ProductSpecificationService service;

    @PostMapping("/")
    public ApiResponse<Boolean> create(@RequestBody @Valid ProductSpecificationRequest request) {
        return ApiResponse.<Boolean>builder()
                .code(1000)
                .result(service.create(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductSpecificationResponse> detail(@PathVariable("id") int id) {
        return ApiResponse.<ProductSpecificationResponse>builder()
                .code(1000)
                .result(service.detail(id))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<List<ProductSpecificationResponse>> list() {
        return ApiResponse.<List<ProductSpecificationResponse>>builder()
                .code(1000)
                .result(service.list())
                .build();
    }

    @GetMapping("/get")
    public ApiResponse<PageResponse<ProductSpecificationResponse>> getPaged(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        return ApiResponse.<PageResponse<ProductSpecificationResponse>>builder()
                .code(1000)
                .result(service.get(page, size))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductSpecificationResponse> update(
            @PathVariable("id") int id,
            @RequestBody ProductSpecificationUpdateRequest request
    ) {
        return ApiResponse.<ProductSpecificationResponse>builder()
                .code(1000)
                .result(service.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") int id) {
        service.delete(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Delete success!")
                .build();
    }
}
