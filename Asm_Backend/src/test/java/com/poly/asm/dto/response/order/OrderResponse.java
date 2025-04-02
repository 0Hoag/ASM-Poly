package com.poly.asm.dto.response.order;

import java.util.List;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
	int id;
    long totalAmount;
    String createdAt;
    List<OrderDetailResponse> orderDetails;
    OrderStatusResponse orderStatus; 
}
