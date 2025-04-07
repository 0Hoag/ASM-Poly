package com.example.ASM.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.ASM.dto.request.OrderStatus.OrderStatusRequest;
import com.example.ASM.dto.request.OrderStatus.OrderStatusUpdateRequest;
import com.example.ASM.dto.response.order.OrderStatusResponse;
import com.example.ASM.entity.OrderStatus;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {
    OrderStatus toOrderStatus(OrderStatusRequest request);

    OrderStatusResponse toOrderStatusResponse(OrderStatus entity);

    void update(@MappingTarget OrderStatus entity, OrderStatusUpdateRequest request);
}
