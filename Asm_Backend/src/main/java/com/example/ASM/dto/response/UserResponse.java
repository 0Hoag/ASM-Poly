package com.example.ASM.dto.response;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private int id;
    private String email;
    private String fullName;
    private String phoneNumber;
    private boolean role;
    private Timestamp createdAt;
}