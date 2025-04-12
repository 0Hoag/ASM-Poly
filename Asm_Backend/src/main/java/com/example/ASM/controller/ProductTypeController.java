package com.example.ASM.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.ProductType.ProductTypeRequest;
import com.example.ASM.dto.response.product.ProductTypeResponse;
import com.example.ASM.service.ProductTypeService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/productType")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductTypeController {
    ProductTypeService productTypeService;

    @PostMapping("/")
    public ApiResponse<ProductTypeResponse> Create(@RequestBody @Valid ProductTypeRequest request) {
        return ApiResponse.<ProductTypeResponse>builder()
                .code(1000)
                .result(productTypeService.Create(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductTypeResponse> Detail(@PathVariable("id") int id) {
        return ApiResponse.<ProductTypeResponse>builder()
                .code(1000)
                .result(productTypeService.Detail(id))
                .build();
    }

    @GetMapping("/List")
    public ApiResponse<List<ProductTypeResponse>> list() {
        return ApiResponse.<List<ProductTypeResponse>>builder()
                .code(1000)
                .result(productTypeService.List())
                .build();
    }

    @GetMapping("/Get")
    public ApiResponse<PageResponse<ProductTypeResponse>> Get(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<ProductTypeResponse>>builder()
                .code(1000)
                .result(productTypeService.Get(page, size))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductTypeResponse> Update(
            @PathVariable("id") int id, @RequestBody ProductTypeRequest request) {
        return ApiResponse.<ProductTypeResponse>builder()
                .code(1000)
                .result(productTypeService.Update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> Delete(@PathVariable("id") int id) {
        productTypeService.Delete(id);
        return ApiResponse.<Void>builder().code(1000).message("Delete success!").build();
    }
}
