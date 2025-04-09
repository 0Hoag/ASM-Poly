package com.example.ASM.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressResponse {
    int id;
    String address;
    String phoneNumber;
    String username;
    boolean defaultAddress;
}
