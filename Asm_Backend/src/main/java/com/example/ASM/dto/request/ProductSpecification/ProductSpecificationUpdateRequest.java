package com.example.ASM.dto.request.ProductSpecification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSpecificationUpdateRequest {
    @NotBlank(message = "Tên thông số sản phẩm không được để trống")
    private String name;

    @NotBlank(message = "Giá trị thông số không được để trống")
    private String value;

    @NotNull(message = "SpecificationType không được để trống")
    private Integer specificationTypeId;

    @NotNull(message = "Product không được để trống")
    private Integer productID;
}
