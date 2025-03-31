package com.example.ASM.dto.request.SpecificationType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecificationTypeRequest {
    @NotBlank(message = "Tên thông số không được để trống")
    String specName;

    @NotNull(message = "ProductType không được để trống")
    Integer productType;
}
