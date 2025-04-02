package com.example.ASM.dto.response;

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
    String userName; // Lấy tên người dùng từ User
    List<CartDetailResponse> cartDetails;
    Timestamp createdAt;
}
