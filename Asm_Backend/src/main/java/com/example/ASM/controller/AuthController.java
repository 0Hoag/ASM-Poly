package com.example.ASM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ASM.dto.ApiResponse;
import com.example.ASM.dto.request.User.ForgotPasswordRequest;
import com.example.ASM.dto.request.User.LoginRequest;
import com.example.ASM.dto.request.User.RegisterRequest;
import com.example.ASM.dto.request.User.ResetPasswordRequest;
import com.example.ASM.dto.response.UserResponse;
import com.example.ASM.service.AuthService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * API đăng ký tài khoản
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody RegisterRequest request) {
        ApiResponse<UserResponse> response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    /**
     * API đăng nhập
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(
            @Valid @RequestBody LoginRequest request,
            HttpSession session) {

        ApiResponse<UserResponse> response = authService.login(request);

        // Nếu đăng nhập thành công, lưu thông tin người dùng vào session
        if (response.getCode() == 1000 && response.getResult() != null) {
            session.setAttribute("currentUser", response.getResult());
            session.setAttribute("isLoggedIn", true);
        }

        return ResponseEntity.ok(response);
    }

    /**
     * API đăng xuất
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpSession session) {
        // Xóa thông tin người dùng khỏi session
        session.invalidate();

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .code(1000)
                        .message("Đăng xuất thành công")
                        .build()
        );
    }

    /**
     * API quên mật khẩu
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<Void>> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        ApiResponse<Void> response = authService.forgotPassword(request);
        return ResponseEntity.ok(response);
    }

    /**
     * API đặt lại mật khẩu
     */
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<Void>> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        ApiResponse<Void> response = authService.resetPassword(request);
        return ResponseEntity.ok(response);
    }

    /**
     * API lấy thông tin người dùng theo email
     */
    @GetMapping("/user/{email}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByEmail(@PathVariable String email) {
        ApiResponse<UserResponse> response = authService.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }

    /**
     * API lấy thông tin người dùng hiện tại
     */
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> getCurrentUser(HttpSession session) {
        // Lấy thông tin người dùng từ session
        UserResponse currentUser = (UserResponse) session.getAttribute("currentUser");

        // Nếu không có người dùng đăng nhập
        if (currentUser == null) {
            return ResponseEntity.ok(
                    ApiResponse.<UserResponse>builder()
                            .code(1001)
                            .message("Không có người dùng đăng nhập")
                            .build()
            );
        }

        // Trả về thông tin người dùng
        return ResponseEntity.ok(
                ApiResponse.<UserResponse>builder()
                        .code(1000)
                        .message("Lấy thông tin người dùng thành công")
                        .result(currentUser)
                        .build()
        );
    }
}

