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
    @NotBlank(message = "Tên thông số không được để trống")
    String name;

    @NotBlank(message = "Giá trị thông số không được để trống")
    String value;

    @NotNull(message = "SpecificationType không được để trống")
    Integer specificationTypeId;
}
