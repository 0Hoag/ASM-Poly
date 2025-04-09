package com.example.ASM.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ASM.dto.request.Auth.ForgotPasswordRequest;
import com.example.ASM.dto.request.Auth.UserLoginRequest;
import com.example.ASM.dto.response.auth.ForgotPasswordResponse;
import com.example.ASM.dto.response.user.UserResponse;
import com.example.ASM.entity.User;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.UserMapper;
import com.example.ASM.repository.UserRepository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthService {
    final UserRepository repo;
    final UserMapper mapper;
    final EmailService emailService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository repo, UserMapper mapper, EmailService emailService) {
        this.repo = repo;
        this.mapper = mapper;
        this.emailService = emailService;
    }

    @Value("${chars.key}")
    String chars;

    public UserResponse login(UserLoginRequest request) {
        if (request.getEmail() == null
                || request.getEmail().isEmpty()
                || request.getPassword() == null
                || request.getPassword().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        User user =
                repo.findByEmail(request.getEmail()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        return mapper.toUserResponse(user);
    }

    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        User user =
                repo.findByEmail(request.getEmail()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        String newPassword = generateRandomPassword();

        user.setPassword(passwordEncoder.encode(newPassword));
        repo.save(user);

        sendPasswordResetEmail(user.getEmail(), newPassword);

        return ForgotPasswordResponse.builder()
                .email(user.getEmail())
                .message("Mật khẩu mới đã được gửi đến email của bạn")
                .build();
    }

    private String generateRandomPassword() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }

        return sb.toString();
    }

    private void sendPasswordResetEmail(String email, String newPassword) {
        String subject = "Mật khẩu mới cho tài khoản của bạn";

        String text = "Xin chào,\n\n"
                + "Mật khẩu mới của bạn là: " + newPassword + "\n\n"
                + "Vui lòng đăng nhập và đổi mật khẩu ngay sau khi đăng nhập thành công.\n\n"
                + "Trân trọng,\n"
                + "Đội ngũ hỗ trợ";

        try {
            emailService.sendEmail(email, subject, text);
        } catch (Exception e) {
            log.error("Lỗi khi gửi email đặt lại mật khẩu: {}", e.getMessage());
            throw new AppException(ErrorCode.SEND_EMAIL_ERROR);
        }
    }
}
