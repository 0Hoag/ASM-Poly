package com.example.ASM.service.build;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.example.ASM.dto.request.Order.OrderRequest;
import com.example.ASM.dto.request.Order.OrderUpdateRequest;
import com.example.ASM.entity.Order;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.OrderMapper;
import com.example.ASM.repository.AddressRepository;
import com.example.ASM.repository.OrderRepository;
import com.example.ASM.repository.OrderStatusRepository;
import com.example.ASM.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@Builder
@Service
@Slf4j
@FieldDefaults(makeFinal = true)
public class OrderBuilder {
    OrderRepository repo;
    OrderMapper mapper;
    OrderStatusRepository orderStatusRepository;
    UserRepository userRepository;
    AddressRepository addressRepository;

    public void processRequest(OrderRequest request) {
        if (request.getTotalAmount() <= 0) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }
        if (!addressRepository.existsById(request.getAddress())) {
            throw new AppException(ErrorCode.ADDRESS_NOT_EXISTED);
        }
        if (!orderStatusRepository.existsById(request.getOrderStatus())) {
            throw new AppException(ErrorCode.ORDERS_STATUS_NOT_EXISTED);
        }
        if (!userRepository.existsById(request.getUser())) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
    }

    public void processUpdateRequest(OrderUpdateRequest request) {
        if (request.getTotalAmount() <= 0) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }
        if (!addressRepository.existsById(request.getAddress())) {
            throw new AppException(ErrorCode.ADDRESS_NOT_EXISTED);
        }
        if (!orderStatusRepository.existsById(request.getOrderStatus())) {
            throw new AppException(ErrorCode.ORDERS_STATUS_NOT_EXISTED);
        }
        if (!userRepository.existsById(request.getUser())) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
    }

    public Order buildOrder(OrderRequest request) {
        Order order = mapper.toOrder(request);
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // Set up relationships for OrderDetails
        if (order.getOrderDetails() != null) {
            order.getOrderDetails().forEach(detail -> detail.setOrder(order));
        }

        return order;
    }
}
