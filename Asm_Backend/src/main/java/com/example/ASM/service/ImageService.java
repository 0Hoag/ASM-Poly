package com.example.ASM.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.ASM.configuration.FileUtils;
import com.example.ASM.dto.request.Image.RemoveProductImage;
import com.example.ASM.entity.Image;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.repository.ImageRepository;
import com.example.ASM.repository.ProductRepository;
import com.example.ASM.service.config.Cloud;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ImageService {
    ImageRepository imageRepository;
    ProductRepository productRepository;

    @NonFinal
    @Value("${cloudinary.cloud-name}")
    protected String cloudName;

    @NonFinal
    @Value("${cloudinary.api-key}")
    protected String apiKey;

    @NonFinal
    @Value("${cloudinary.api-secret}")
    protected String apiSecret;

    @Bean
    public Cloudinary cloudinaryConfig() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }

    public void uploadImageProduct(int productID, MultipartFile file) throws IOException, SQLException {
        var product = productRepository.findById(productID)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        if (!FileUtils.validateFile(file)) {
            log.error("Invalid file: {}", file.getOriginalFilename());
            throw new AppException(ErrorCode.UPLOAD_FILE_FAIL);
        }
        log.info("OriginalFilename: {}", file.getOriginalFilename()); // type file ex: image

        try {
            Map<String, Object> uploadOptions = new HashMap<>();
            Map<String, Object> uploadResult = cloudinaryConfig()
                    .uploader()
                    .upload(file.getBytes(), uploadOptions);

            String publicId = (String) uploadResult.get("public_id");

            String image1080pUrl = cloudinaryConfig().url()
                    .transformation(new Transformation().width(1080).crop("scale"))
                    .generate(publicId);

            var image = imageRepository.save(Image.builder().product(product).url(image1080pUrl).build());
            product.getImages().add(image);

            productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
    }

    public void removeImageProduct(int productID) {
        var product = productRepository.findById(productID)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        ArrayList<Image> productImages = new ArrayList<>(product.getImages());
        for (Image url: productImages) {
            try {
                String publicId = extractPublicIdFromUrl(url.getUrl());
                log.info("publicId: {}", publicId);

                Map<String, Object> params = new HashMap<>();

                Map result = cloudinaryConfig().uploader().destroy(publicId, params);

                imageRepository.deleteById(url.getId());

                if ("ok".equals(result.get("result"))) {
                    log.info("Successfully delete media from Cloudinary");
//                    product.getImages().remove(url.getUrl());

                }else {
                    log.error("Failed to delete media from Cloudinary");
                    throw new AppException(ErrorCode.REMOVE_FILE_FAIL);
                }
            } catch (IOException e) {
                throw new AppException(ErrorCode.REMOVE_FILE_FAIL);
            }
        }
    }

    public void removeImageChoose(int productID, RemoveProductImage image) {
        var product = productRepository.findById(productID)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        Set<Image> images =
                imageRepository.findAllById(image.getImages()).stream().collect(Collectors.toSet());

        for (Image url : images) {
            try {
                String publicId = extractPublicIdFromUrl(url.getUrl());
                log.info("publicId: {}", publicId);

                Map result = cloudinaryConfig().uploader().destroy(publicId, ObjectUtils.emptyMap());

                imageRepository.deleteById(url.getId());

                if ("ok".equals(result.get("result"))) {
                    log.info("Successfully delete image from Cloudinary: {}", publicId);
//                    product.getImages().remove(url.getUrl());
                } else {
                    log.error("Failed to delete image from Cloudinary: {}", publicId);
                    throw new AppException(ErrorCode.REMOVE_FILE_FAIL);
                }

            } catch (IOException e) {
                throw new AppException(ErrorCode.REMOVE_FILE_FAIL);
            }
        }

        productRepository.save(product);
    }

    public String extractPublicIdFromUrl(String imageUrl) {
        String[] urlParts = imageUrl.split("/");
        int uploadIndex = -1;
        for (int i = 0; i < urlParts.length; i++) {
            if ("upload".equals(urlParts[i])) {
                uploadIndex = i;
                break;
            }
        }

        if (uploadIndex == -1 || uploadIndex == urlParts.length - 1) {
            throw new IllegalArgumentException("Invalid Cloudinary URL format");
        }

        return String.join("/", Arrays.stream(urlParts, uploadIndex + 1, urlParts.length)
                        .filter(part -> !part.startsWith("v"))
                        .collect(Collectors.toList()))
                .replaceFirst("[.][^.]+$", "");
    }

}
