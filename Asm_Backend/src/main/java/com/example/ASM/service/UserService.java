package com.example.ASM.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.ASM.dto.request.User.ForgotPasswordRequest;
import com.example.ASM.dto.request.User.UserLoginRequest;
import com.example.ASM.dto.request.User.UserRegisterRequest;
import com.example.ASM.dto.response.ForgotPasswordResponse;
import com.example.ASM.dto.response.UserResponse;
import com.example.ASM.entity.User;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.UserMapper;
import com.example.ASM.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserMapper mapper;
    UserRepository repo;

    public UserResponse register(UserRegisterRequest request) {
        // Kiểm tra dữ liệu đầu vào
        if (request.getEmail() == null || request.getEmail().isEmpty() ||
                request.getPassword() == null || request.getPassword().isEmpty() ||
                request.getFullName() == null || request.getFullName().isEmpty() ||
                request.getPhoneNumber() == null || request.getPhoneNumber().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        // Kiểm tra email đã tồn tại chưa
        if (repo.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXITSTED);
        }

        // Kiểm tra số điện thoại đã tồn tại chưa
        if (repo.existsByPhoneNumber(request.getPhoneNumber())) {
            // Sử dụng USER_EXITSTED vì không có mã lỗi cụ thể cho số điện thoại
            throw new AppException(ErrorCode.USER_EXITSTED);
        }

        // Chuyển đổi request thành entity và lưu vào database
        User user = mapper.toUser(request);
        user.setCreatedAt(new Timestamp(Instant.now().toEpochMilli()));
        user.setRole(false); // Mặc định là user thường
        User savedUser = repo.save(user);

        // Trả về thông tin người dùng đã đăng ký
        return mapper.toUserResponse(savedUser);
    }

    public UserResponse login(UserLoginRequest request) {
        // Kiểm tra dữ liệu đầu vào
        if (request.getEmail() == null || request.getEmail().isEmpty() ||
                request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        // Tìm user theo email
        User user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Kiểm tra mật khẩu (so sánh trực tiếp không mã hóa)
        if (!request.getPassword().equals(user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        // Trả về thông tin người dùng đã đăng nhập
        return mapper.toUserResponse(user);
    }

    public UserResponse getUserById(int id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return mapper.toUserResponse(user);
    }

    public UserResponse getUserByEmail(String email) {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return mapper.toUserResponse(user);
    }

    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request) {
        // Kiểm tra dữ liệu đầu vào
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        // Tìm user theo email
        User user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Tạo mật khẩu mới ngẫu nhiên
        String newPassword = generateRandomPassword();

        // Cập nhật mật khẩu mới cho user
        user.setPassword(newPassword);
        repo.save(user);

        // Trong thực tế, bạn sẽ gửi email với mật khẩu mới
        // Ở đây chúng ta chỉ trả về mật khẩu mới để demo
        return ForgotPasswordResponse.builder()
                .email(user.getEmail())
                .newPassword(newPassword)
                .message("Mật khẩu mới đã được tạo thành công")
                .build();
    }

    // Phương thức tạo mật khẩu ngẫu nhiên
    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        // Tạo mật khẩu ngẫu nhiên có độ dài 6   ký tự
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }

        return sb.toString();
    }
}