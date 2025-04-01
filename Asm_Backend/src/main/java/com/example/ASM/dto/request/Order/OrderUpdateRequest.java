package com.example.ASM.dto.request.Order;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderUpdateRequest {
    double totalAmount;
    int address;
    int orderStatus;
    int user; // chua lay duoc userID tai vi chua lam xong user
}
