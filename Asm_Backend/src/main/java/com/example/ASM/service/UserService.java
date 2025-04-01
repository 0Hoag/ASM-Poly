package com.example.ASM.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.request.User.ForgotPasswordRequest;
import com.example.ASM.dto.request.User.LoginRequest;
import com.example.ASM.dto.request.User.RegisterRequest;
import com.example.ASM.dto.request.User.ResetPasswordRequest;
import com.example.ASM.dto.response.UserResponse;
import com.example.ASM.entity.User;
import com.example.ASM.exception.BadRequestException;
import com.example.ASM.exception.ResourceNotFoundException;
import com.example.ASM.mapper.UserMapper;
import com.example.ASM.repository.UserRepository;
import com.example.ASM.util.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailService emailService;

    // Lưu trữ token đặt lại mật khẩu tạm thời (trong thực tế nên lưu vào database)
    private final Map<String, String> resetPasswordTokens = new HashMap<>();

    /**
     * Đăng ký tài khoản mới
     */
    public ApiResponse<UserResponse> register(RegisterRequest request) {
        // Kiểm tra email đã tồn tại chưa
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email đã được đăng ký");
        }

        // Kiểm tra số điện thoại đã tồn tại chưa
        if (userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            throw new BadRequestException("Số điện thoại đã được đăng ký");
        }

        // Mã hóa mật khẩu
        String encodedPassword = PasswordEncoder.encode(request.getPassword());

        // Tạo user mới
        User user = userMapper.toEntity(request);
        user.setPassword(encodedPassword);
        user.setCreatedAt(Timestamp.from(Instant.now()));

        // Lưu user
        User savedUser = userRepository.save(user);

        // Tạo response
        UserResponse userResponse = userMapper.toResponse(savedUser);

        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .message("Đăng ký thành công")
                .result(userResponse)
                .build();
    }

    /**
     * Đăng nhập
     */
    public ApiResponse<UserResponse> login(LoginRequest request) {
        // Tìm user theo email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Email hoặc mật khẩu không chính xác"));

        // Kiểm tra mật khẩu
        if (!PasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Email hoặc mật khẩu không chính xác");
        }

        // Tạo response
        UserResponse userResponse = userMapper.toResponse(user);

        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .message("Đăng nhập thành công")
                .result(userResponse)
                .build();
    }

    /**
     * Quên mật khẩu
     */
    public ApiResponse<Void> forgotPassword(ForgotPasswordRequest request) {
        // Tìm user theo email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản với email này"));

        // Tạo token đặt lại mật khẩu
        String resetToken = UUID.randomUUID().toString();
        resetPasswordTokens.put(resetToken, user.getEmail());

        // Gửi email đặt lại mật khẩu
        String resetLink = "http://localhost:5173/reset-password?token=" + resetToken;
        String emailContent = "Xin chào " + user.getFullName() + ",\n\n"
                + "Bạn đã yêu cầu đặt lại mật khẩu. Vui lòng nhấp vào liên kết sau để đặt lại mật khẩu của bạn:\n\n"
                + resetLink + "\n\n"
                + "Liên kết này sẽ hết hạn sau 30 phút.\n\n"
                + "Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.";

        emailService.sendEmail(user.getEmail(), "Đặt lại mật khẩu", emailContent);

        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Đã gửi email đặt lại mật khẩu. Vui lòng kiểm tra hộp thư của bạn.")
                .build();
    }

    /**
     * Đặt lại mật khẩu
     */
    public ApiResponse<Void> resetPassword(ResetPasswordRequest request) {
        // Kiểm tra token
        String email = resetPasswordTokens.get(request.getToken());
        if (email == null) {
            throw new BadRequestException("Token không hợp lệ hoặc đã hết hạn");
        }

        // Kiểm tra mật khẩu xác nhận
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Mật khẩu xác nhận không khớp");
        }

        // Tìm user theo email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản"));

        // Cập nhật mật khẩu
        user.setPassword(PasswordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        // Xóa token
        resetPasswordTokens.remove(request.getToken());

        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Đặt lại mật khẩu thành công")
                .build();
    }

    /**
     * Lấy thông tin người dùng theo email
     */
    public ApiResponse<UserResponse> getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản"));

        UserResponse userResponse = userMapper.toResponse(user);

        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .message("Lấy thông tin người dùng thành công")
                .result(userResponse)
                .build();
    }
}

