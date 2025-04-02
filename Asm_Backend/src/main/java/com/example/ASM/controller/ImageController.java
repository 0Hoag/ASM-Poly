package com.example.ASM.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.request.Image.RemoveProductImage;
import com.example.ASM.service.ImageService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/image")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageController {
    ImageService imageService;

    @PostMapping("/{productID}")
    ApiResponse<Void> uploadImageToProduct(
            @PathVariable("productID") int productID, @RequestPart("file") MultipartFile[] file)
            throws IOException, SQLException {
        imageService.uploadImageProduct(productID, file);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Update image success")
                .build();
    }

    @DeleteMapping("/{productID}")
    public ApiResponse<Void> removeImageChoose(
            @PathVariable("productID") int productID, @RequestBody RemoveProductImage request) {
        imageService.removeImageChoose(productID, request);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Remove image choose success!")
                .build();
    }
}
