package com.example.ASM.dto.response.cart;

import java.sql.Timestamp;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
    int id;
    int userId;
    String userName; // Lấy tên người dùng từ User
    List<CartDetailResponse> cartDetails;
    Timestamp createdAt;
}
