package com.example.ASM.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationRequest;
import com.example.ASM.dto.request.ProductSpecification.ProductSpecificationUpdateRequest;
import com.example.ASM.dto.response.product.ProductSpecificationResponse;
import com.example.ASM.service.ProductSpecificationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product-specification")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductSpecificationController {
    ProductSpecificationService productSpecificationService;

    @PostMapping("/")
    public ApiResponse<Boolean> create(@RequestBody @Valid ProductSpecificationRequest request) {
        return ApiResponse.<Boolean>builder()
                .code(1000)
                .result(productSpecificationService.Create(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductSpecificationResponse> detail(@PathVariable("id") int id) {
        return ApiResponse.<ProductSpecificationResponse>builder()
                .code(1000)
                .result(productSpecificationService.Detail(id))
                .build();
    }

    @GetMapping("/getByName/{name}")
    public ApiResponse<ProductSpecificationResponse> getByName(@PathVariable("name") String name) {
        return ApiResponse.<ProductSpecificationResponse>builder()
                .code(1000)
                .result(productSpecificationService.GetByName(name))
                .build();
    }

    @GetMapping("/List")
    public ApiResponse<List<ProductSpecificationResponse>> list() {
        return ApiResponse.<List<ProductSpecificationResponse>>builder()
                .code(1000)
                .result(productSpecificationService.List())
                .build();
    }

    @GetMapping("/Get")
    public ApiResponse<PageResponse<ProductSpecificationResponse>> getPaged(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<ProductSpecificationResponse>>builder()
                .code(1000)
                .result(productSpecificationService.Get(page, size))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductSpecificationResponse> update(
            @PathVariable("id") int id, @RequestBody ProductSpecificationUpdateRequest request) {
        return ApiResponse.<ProductSpecificationResponse>builder()
                .code(1000)
                .result(productSpecificationService.Update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") int id) {
        productSpecificationService.Delete(id);
        return ApiResponse.<Void>builder().code(1000).message("Delete success!").build();
    }
}
