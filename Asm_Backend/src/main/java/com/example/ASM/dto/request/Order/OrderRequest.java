package com.example.ASM.dto.request.Order;

import com.example.ASM.dto.request.OrderDetail.OrderDetailRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    double totalAmount;
    int address;
    int orderStatus;
    int user;
    List<OrderDetailRequest> orderDetails;
}
