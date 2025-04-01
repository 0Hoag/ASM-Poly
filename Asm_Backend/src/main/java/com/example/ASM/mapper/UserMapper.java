package com.example.ASM.mapper;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.stereotype.Component;

import com.example.ASM.dto.request.User.RegisterRequest;
import com.example.ASM.dto.response.UserResponse;
import com.example.ASM.entity.User;

@Component
public class UserMapper {

    public User toEntity(RegisterRequest request) {
        return User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(request.getPassword()) // Lưu ý: Mật khẩu sẽ được mã hóa trước khi lưu
                .phoneNumber(request.getPhoneNumber())
                .role(false) // Mặc định là user thường
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.isRole())
                .build();
    }
}
