package com.poly.asm.service;

import java.util.List;

import com.poly.asm.dto.response.order.OrderResponse;

public interface OrderService {
	List<OrderResponse> getOrdersByUserId(int userId);
}
