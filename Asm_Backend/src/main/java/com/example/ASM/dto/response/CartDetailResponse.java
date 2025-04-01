package com.example.ASM.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDetailResponse {
    int id;
    int quantity;
    String productName;
    Integer cart;
}
