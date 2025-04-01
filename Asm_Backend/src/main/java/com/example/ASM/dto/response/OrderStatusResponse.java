package com.example.ASM.dto.response;

import java.util.List;

import com.example.ASM.entity.Order;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderStatusResponse {
    int id;
    String statusName;
    List<Order> orders;
}
