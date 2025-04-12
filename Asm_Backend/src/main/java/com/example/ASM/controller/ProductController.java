package com.example.ASM.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.Product.ProductGetRequest;
import com.example.ASM.dto.request.Product.ProductRequest;
import com.example.ASM.dto.request.Product.ProductUpdateRequest;
import com.example.ASM.dto.response.product.ProductResponse;
import com.example.ASM.service.ProductService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @PostMapping("/")
    public ApiResponse<ProductResponse> create(@RequestBody @Valid ProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .code(1000)
                .result(productService.Create(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> detail(@PathVariable("id") int id) {
        return ApiResponse.<ProductResponse>builder()
                .code(1000)
                .result(productService.Detail(id))
                .build();
    }

    @GetMapping("/List")
    public ApiResponse<List<ProductResponse>> list() {
        return ApiResponse.<List<ProductResponse>>builder()
                .code(1000)
                .result(productService.List())
                .build();
    }

    @GetMapping("/Get")
    public ApiResponse<PageResponse<ProductResponse>> getPaged(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<ProductResponse>>builder()
                .code(1000)
                .result(productService.Get(page, size))
                .build();
    }

    @GetMapping("/GetMany")
    public ApiResponse<List<ProductResponse>> getMany(@RequestBody ProductGetRequest request) {
        return ApiResponse.<List<ProductResponse>>builder()
                .code(1000)
                .result(productService.GetMany(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> update(@PathVariable("id") int id, @RequestBody ProductUpdateRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .code(1000)
                .result(productService.Update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") int id) {
        productService.Delete(id);
        return ApiResponse.<Void>builder().code(1000).message("Delete success!").build();
    }
}
