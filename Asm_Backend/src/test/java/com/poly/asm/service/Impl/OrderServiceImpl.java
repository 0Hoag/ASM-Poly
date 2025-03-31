package com.poly.asm.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm.dto.response.order.OrderResponse;
import com.poly.asm.entity.Order;
import com.poly.asm.mapper.OrderMapper;
import com.poly.asm.repository.OrderRepository;
import com.poly.asm.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;
	@Override
	public List<OrderResponse> getOrdersByUserId(int userId) {
		List<Order> orders = orderRepository.findByUserId(userId);
		return orders.stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
	}

}
