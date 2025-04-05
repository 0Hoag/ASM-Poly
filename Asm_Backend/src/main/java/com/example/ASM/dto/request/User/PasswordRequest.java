package com.example.ASM.dto.request.User;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordRequest {
    String password;
    String newPass;
    String confirmPassword;
}
