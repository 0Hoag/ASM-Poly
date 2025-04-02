package com.example.ASM.dto.request.CartDetail;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDetailRequest {
    @NotNull(message = "ProductId không được để trống")
    Integer product;

    @NotNull(message = "CartID không được để trống")
    Integer cart;

    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    int quantity;
}
