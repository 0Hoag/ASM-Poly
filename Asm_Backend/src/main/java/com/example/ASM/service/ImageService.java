package com.example.ASM.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.example.ASM.configuration.FileUtils;
import com.example.ASM.dto.request.Image.RemoveProductImage;
import com.example.ASM.entity.Image;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.repository.ImageRepository;
import com.example.ASM.repository.ProductRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

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

    public void uploadImageProduct(int productID, MultipartFile[] files) throws IOException, SQLException {
        var product = productRepository
                .findById(productID)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        if (files == null || files.length == 0) {
            throw new AppException(ErrorCode.UPLOAD_FILE_FAIL);
        }

        for (MultipartFile file : files) {
            if (!FileUtils.validateFile(file)) {
                throw new AppException(ErrorCode.UPLOAD_FILE_FAIL);
            }

            try {
                Map<String, Object> uploadOptions = new HashMap<>();
                Map<String, Object> uploadResult = cloudinaryConfig().uploader().upload(file.getBytes(), uploadOptions);

                String publicId = (String) uploadResult.get("public_id");

                String image1080pUrl = cloudinaryConfig()
                        .url()
                        .transformation(new Transformation().width(1080).crop("scale"))
                        .generate(publicId);

                var image = imageRepository.save(
                        Image.builder().product(product).url(image1080pUrl).build());
                product.getImages().add(image);
            } catch (DataIntegrityViolationException e) {
                throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
            }
        }

        productRepository.save(product);
    }

    public void removeImageProduct(int productID) {
        var product = productRepository
                .findById(productID)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        ArrayList<Image> productImages = new ArrayList<>(product.getImages());
        for (Image url : productImages) {
            try {
                String publicId = extractPublicIdFromUrl(url.getUrl());

                Map<String, Object> params = new HashMap<>();

                Map result = cloudinaryConfig().uploader().destroy(publicId, params);

                imageRepository.deleteById(url.getId());

                if ("ok".equals(result.get("result"))) {
                    log.info("Successfully delete media from Cloudinary");
                } else {
                    log.error("Failed to delete media from Cloudinary");
                    throw new AppException(ErrorCode.REMOVE_FILE_FAIL);
                }
            } catch (IOException e) {
                throw new AppException(ErrorCode.REMOVE_FILE_FAIL);
            }
        }
    }

    public void removeImageChoose(int productID, RemoveProductImage image) {
        var product = productRepository
                .findById(productID)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        List<Image> imagesToRemove = new ArrayList<>(imageRepository.findAllById(image.getImages()));

        if (imagesToRemove.isEmpty()) return;

        for (Image img : imagesToRemove) {
            product.getImages().removeIf(productImage -> productImage.getId() == img.getId());
        }

        productRepository.save(product);

        for (Image img : imagesToRemove) {
            try {
                String publicId = extractPublicIdFromUrl(img.getUrl());

                Map<String, Object> params = new HashMap<>();
                Map result = cloudinaryConfig().uploader().destroy(publicId, params);

                imageRepository.deleteById(img.getId());

                if ("ok".equals(result.get("result"))) {
                    log.info("Successfully deleted image from Cloudinary: {}", publicId);
                } else {
                    log.error("Failed to delete image from Cloudinary: {}", publicId);
                    throw new AppException(ErrorCode.REMOVE_FILE_FAIL);
                }
            } catch (IOException e) {
                throw new AppException(ErrorCode.REMOVE_FILE_FAIL);
            }
        }
    }

    public String extractPublicIdFromUrl(String imageUrl) {
        String filename = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);

        if (filename.contains(".")) {
            filename = filename.substring(0, filename.lastIndexOf('.'));
        }

        return filename;
    }
}
