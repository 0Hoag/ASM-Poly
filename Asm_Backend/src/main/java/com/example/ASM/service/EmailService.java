package com.example.ASM.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    JavaMailSender mailSender;

    /**
     * Gửi email đơn giản
     *
     * @param to      Địa chỉ email người nhận
     * @param subject Tiêu đề email
     * @param text    Nội dung email
     */
    public void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("noreply@example.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            mailSender.send(message);

            System.out.println("Đã gửi email đến: " + to);
        } catch (Exception e) {
            System.err.println("Lỗi khi gửi email: " + e.getMessage());
        }
    }

    /**
     * Gửi email đặt lại mật khẩu
     *
     * @param to          Địa chỉ email người nhận
     * @param newPassword Mật khẩu mới
     */
    public void sendPasswordResetEmail(String to, String newPassword) {
        String subject = "Mật khẩu mới cho tài khoản của bạn";

        String text = "Xin chào,\n\n"
                + "Mật khẩu mới của bạn là: " + newPassword + "\n\n"
                + "Vui lòng đăng nhập và đổi mật khẩu ngay sau khi đăng nhập thành công.\n\n"
                + "Trân trọng,\n"
                + "Đội ngũ hỗ trợ";

        sendEmail(to, subject, text);
    }
}
