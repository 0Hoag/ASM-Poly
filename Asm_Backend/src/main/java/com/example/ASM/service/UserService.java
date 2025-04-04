package com.example.ASM.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    // Thêm EmailService
    @Autowired
    private EmailService emailService;

    // Thêm PasswordEncoder để mã hóa mật khẩu
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
        // Mã hóa mật khẩu trước khi lưu
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(new Timestamp(Instant.now().toEpochMilli()));
        user.setRole(false); // Mặc định là khách hàng (false)
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

        // Kiểm tra mật khẩu (sử dụng passwordEncoder để so sánh)
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        // Trả về thông tin người dùng đã đăng nhập, bao gồm vai trò
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

        // Lưu mật khẩu đã mã hóa vào database
        user.setPassword(passwordEncoder.encode(newPassword));
        repo.save(user);

        // Gửi email với mật khẩu mới
        sendPasswordResetEmail(user.getEmail(), newPassword);

        // Trả về thông báo thành công (KHÔNG trả về mật khẩu mới)
        return ForgotPasswordResponse.builder()
                .email(user.getEmail())
                .message("Mật khẩu mới đã được gửi đến email của bạn")
                .build();
    }

    // Phương thức gửi email đặt lại mật khẩu
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
            // Vẫn tiếp tục xử lý ngay cả khi gửi email thất bại
        }
    }

    // Phương thức tạo mật khẩu ngẫu nhiên
    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        // Tạo mật khẩu ngẫu nhiên có độ dài 8 ký tự (tăng từ 6 lên 8 để an toàn hơn)
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }

        return sb.toString();
    }

    // Phương thức kiểm tra vai trò người dùng
    public boolean isAdmin(int userId) {
        User user = repo.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return user.isRole(); // true là admin, false là khách hàng
    }

    // Phương thức kiểm tra vai trò người dùng theo email
    public boolean isAdmin(String email) {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return user.isRole(); // true là admin, false là khách hàng
    }
}