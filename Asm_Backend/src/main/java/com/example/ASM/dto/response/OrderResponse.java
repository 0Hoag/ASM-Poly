package com.example.ASM.dto.response;

import com.example.ASM.entity.Order;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    int id;
    Timestamp createdAt;
    double totalAmount;
    List<String> orderDetails;
    int address;
    String orderStatus; // take name
    int user;
}
