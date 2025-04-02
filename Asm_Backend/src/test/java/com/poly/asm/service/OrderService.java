package com.poly.asm.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.asm.dto.PageResponse;
import com.poly.asm.dto.response.order.OrderResponse;
import com.poly.asm.mapper.OrderMapper;
import com.poly.asm.repository.OrderRepository;
import com.poly.asm.service.OrderService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService{
	OrderRepository orderRepository;
    OrderMapper orderMapper;
    
	public PageResponse<OrderResponse> getOrdersByUserId(int userId, int page, int size) {
		Pageable pageable = PageRequest.of(page -1, size);
		var pageData = orderRepository.findByUserId(userId, pageable);
		
		var data = pageData.getContent().stream()
				.map(orderMapper::toOrderResponse)
				.collect(Collectors.toList());
		return PageResponse.<OrderResponse>builder()
				.currentPage(page)
				.totalPages(pageData.getTotalPages())
				.pageSize(pageData.getSize())
				.totalElements(pageData.getTotalElements())
				.data(data)
				.build();
	}
}
