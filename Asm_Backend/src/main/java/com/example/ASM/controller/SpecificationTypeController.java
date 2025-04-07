package com.example.ASM.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.SpecificationType.SpecificationTypeRequest;
import com.example.ASM.dto.request.SpecificationType.SpecificationTypeUpdateRequest;
import com.example.ASM.dto.response.product.SpecificationTypeResponse;
import com.example.ASM.service.SpecificationTypeService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/specificationType")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpecificationTypeController {
    SpecificationTypeService service;

    @PostMapping("/")
    public ApiResponse<Boolean> create(@RequestBody @Valid SpecificationTypeRequest request) {
        return ApiResponse.<Boolean>builder()
                .code(1000)
                .result(service.Create(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<SpecificationTypeResponse> detail(@PathVariable("id") int id) {
        return ApiResponse.<SpecificationTypeResponse>builder()
                .code(1000)
                .result(service.Detail(id))
                .build();
    }

    @GetMapping("/List")
    public ApiResponse<List<SpecificationTypeResponse>> list() {
        return ApiResponse.<List<SpecificationTypeResponse>>builder()
                .code(1000)
                .result(service.List())
                .build();
    }

    @GetMapping("/Get")
    public ApiResponse<PageResponse<SpecificationTypeResponse>> getPaged(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ApiResponse.<PageResponse<SpecificationTypeResponse>>builder()
                .code(1000)
                .result(service.Get(page, size))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<SpecificationTypeResponse> update(
            @PathVariable("id") int id, @RequestBody SpecificationTypeUpdateRequest request) {
        return ApiResponse.<SpecificationTypeResponse>builder()
                .code(1000)
                .result(service.Update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") int id) {
        service.Delete(id);
        return ApiResponse.<Void>builder().code(1000).message("Delete success!").build();
    }
}
