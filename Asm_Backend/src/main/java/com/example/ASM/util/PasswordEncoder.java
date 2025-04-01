package com.example.ASM.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncoder {

    private static final int SALT_LENGTH = 16;

    public static String encode(String password) {
        try {
            // Tạo salt ngẫu nhiên
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);

            // Mã hóa mật khẩu với salt
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());

            // Kết hợp salt và mật khẩu đã mã hóa
            byte[] combined = new byte[salt.length + hashedPassword.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(hashedPassword, 0, combined, salt.length, hashedPassword.length);

            // Mã hóa Base64 để lưu trữ
            return Base64.getEncoder().encodeToString(combined);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Lỗi khi mã hóa mật khẩu", e);
        }
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        try {
            // Giải mã Base64
            byte[] combined = Base64.getDecoder().decode(encodedPassword);

            // Tách salt và mật khẩu đã mã hóa
            byte[] salt = new byte[SALT_LENGTH];
            byte[] hashedPassword = new byte[combined.length - SALT_LENGTH];
            System.arraycopy(combined, 0, salt, 0, salt.length);
            System.arraycopy(combined, salt.length, hashedPassword, 0, hashedPassword.length);

            // Mã hóa mật khẩu nhập vào với salt đã lưu
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] newHashedPassword = md.digest(rawPassword.getBytes());

            // So sánh hai mật khẩu đã mã hóa
            return MessageDigest.isEqual(hashedPassword, newHashedPassword);
        } catch (NoSuchAlgorithmException | IllegalArgumentException e) {
            return false;
        }
    }
}

