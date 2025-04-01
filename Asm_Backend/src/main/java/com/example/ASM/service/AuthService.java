package com.example.ASM.service;

import java.security.MessageDigest;
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
import com.example.ASM.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    // Lưu trữ token đặt lại mật khẩu (trong thực tế nên lưu vào database)
    private Map<String, String> resetPasswordTokens = new HashMap<>();

    /**
     * Đăng ký tài khoản mới
     */
    public ApiResponse<UserResponse> register(RegisterRequest request) {
        // Kiểm tra email đã tồn tại chưa
        if (userRepository.existsByEmail(request.getEmail())) {
            return ApiResponse.<UserResponse>builder()
                    .code(1001)
                    .message("Email đã được đăng ký")
                    .build();
        }

        // Kiểm tra số điện thoại đã tồn tại chưa
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            return ApiResponse.<UserResponse>builder()
                    .code(1001)
                    .message("Số điện thoại đã được đăng ký")
                    .build();
        }

        try {
            // Tạo đối tượng User mới
            User user = new User();
            user.setEmail(request.getEmail());
            user.setFullName(request.getFullName());
            // Mã hóa mật khẩu bằng phương thức đơn giản
            user.setPassword(encodePassword(request.getPassword()));
            user.setPhoneNumber(request.getPhoneNumber());
            user.setRole(false); // Mặc định là user thường
            user.setCreatedAt(Timestamp.from(Instant.now()));

            // Lưu user vào database
            User savedUser = userRepository.save(user);

            // Tạo đối tượng UserResponse để trả về
            UserResponse userResponse = convertToUserResponse(savedUser);

            // Trả về kết quả thành công
            return ApiResponse.<UserResponse>builder()
                    .code(1000)
                    .message("Đăng ký thành công")
                    .result(userResponse)
                    .build();

        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return ApiResponse.<UserResponse>builder()
                    .code(1002)
                    .message("Đăng ký thất bại: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Đăng nhập
     */
    public ApiResponse<UserResponse> login(LoginRequest request) {
        try {
            // Tìm user theo email
            Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

            // Kiểm tra user có tồn tại không
            if (userOptional.isEmpty()) {
                return ApiResponse.<UserResponse>builder()
                        .code(1001)
                        .message("Email hoặc mật khẩu không chính xác")
                        .build();
            }

            User user = userOptional.get();

            // Kiểm tra mật khẩu
            if (!checkPassword(request.getPassword(), user.getPassword())) {
                return ApiResponse.<UserResponse>builder()
                        .code(1001)
                        .message("Email hoặc mật khẩu không chính xác")
                        .build();
            }

            // Tạo đối tượng UserResponse để trả về
            UserResponse userResponse = convertToUserResponse(user);

            // Trả về kết quả thành công
            return ApiResponse.<UserResponse>builder()
                    .code(1000)
                    .message("Đăng nhập thành công")
                    .result(userResponse)
                    .build();

        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return ApiResponse.<UserResponse>builder()
                    .code(1002)
                    .message("Đăng nhập thất bại: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Quên mật khẩu
     */
    public ApiResponse<Void> forgotPassword(ForgotPasswordRequest request) {
        try {
            // Tìm user theo email
            Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

            // Kiểm tra user có tồn tại không
            if (userOptional.isEmpty()) {
                return ApiResponse.<Void>builder()
                        .code(1001)
                        .message("Không tìm thấy tài khoản với email này")
                        .build();
            }

            User user = userOptional.get();

            // Tạo token đặt lại mật khẩu
            String resetToken = UUID.randomUUID().toString();
            resetPasswordTokens.put(resetToken, user.getEmail());

            // Tạo link đặt lại mật khẩu
            String resetLink = "http://localhost:5173/reset-password?token=" + resetToken;

            // Nội dung email
            String emailContent = "Xin chào " + user.getFullName() + ",\n\n"
                    + "Bạn đã yêu cầu đặt lại mật khẩu. Vui lòng nhấp vào liên kết sau để đặt lại mật khẩu của bạn:\n\n"
                    + resetLink + "\n\n"
                    + "Liên kết này sẽ hết hạn sau 30 phút.\n\n"
                    + "Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.";

            // Gửi email
            emailService.sendEmail(user.getEmail(), "Đặt lại mật khẩu", emailContent);

            // Trả về kết quả thành công
            return ApiResponse.<Void>builder()
                    .code(1000)
                    .message("Đã gửi email đặt lại mật khẩu. Vui lòng kiểm tra hộp thư của bạn.")
                    .build();

        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return ApiResponse.<Void>builder()
                    .code(1002)
                    .message("Gửi email đặt lại mật khẩu thất bại: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Đặt lại mật khẩu
     */
    public ApiResponse<Void> resetPassword(ResetPasswordRequest request) {
        try {
            // Kiểm tra token có hợp lệ không
            String email = resetPasswordTokens.get(request.getToken());
            if (email == null) {
                return ApiResponse.<Void>builder()
                        .code(1001)
                        .message("Token không hợp lệ hoặc đã hết hạn")
                        .build();
            }

            // Kiểm tra mật khẩu xác nhận
            if (!request.getNewPassword().equals(request.getConfirmPassword())) {
                return ApiResponse.<Void>builder()
                        .code(1001)
                        .message("Mật khẩu xác nhận không khớp")
                        .build();
            }

            // Tìm user theo email
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isEmpty()) {
                return ApiResponse.<Void>builder()
                        .code(1001)
                        .message("Không tìm thấy tài khoản")
                        .build();
            }

            User user = userOptional.get();

            // Cập nhật mật khẩu mới
            user.setPassword(encodePassword(request.getNewPassword()));
            userRepository.save(user);

            // Xóa token
            resetPasswordTokens.remove(request.getToken());

            // Trả về kết quả thành công
            return ApiResponse.<Void>builder()
                    .code(1000)
                    .message("Đặt lại mật khẩu thành công")
                    .build();

        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return ApiResponse.<Void>builder()
                    .code(1002)
                    .message("Đặt lại mật khẩu thất bại: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Lấy thông tin người dùng theo email
     */
    public ApiResponse<UserResponse> getUserByEmail(String email) {
        try {
            // Tìm user theo email
            Optional<User> userOptional = userRepository.findByEmail(email);

            // Kiểm tra user có tồn tại không
            if (userOptional.isEmpty()) {
                return ApiResponse.<UserResponse>builder()
                        .code(1001)
                        .message("Không tìm thấy tài khoản")
                        .build();
            }

            User user = userOptional.get();

            // Tạo đối tượng UserResponse để trả về
            UserResponse userResponse = convertToUserResponse(user);

            // Trả về kết quả thành công
            return ApiResponse.<UserResponse>builder()
                    .code(1000)
                    .message("Lấy thông tin người dùng thành công")
                    .result(userResponse)
                    .build();

        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return ApiResponse.<UserResponse>builder()
                    .code(1002)
                    .message("Lấy thông tin người dùng thất bại: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Chuyển đổi từ User sang UserResponse
     */
    private UserResponse convertToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.isRole())
                .build();
    }

    /**
     * Mã hóa mật khẩu đơn giản
     */
    private String encodePassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi mã hóa mật khẩu", e);
        }
    }

    /**
     * Kiểm tra mật khẩu
     */
    private boolean checkPassword(String rawPassword, String encodedPassword) {
        String encodedRawPassword = encodePassword(rawPassword);
        return encodedRawPassword.equals(encodedPassword);
    }
}

