package com.example.ASM.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.ASM.dto.request.OrderStatus.OrderStatusRequest;
import com.example.ASM.dto.request.OrderStatus.OrderStatusUpdateRequest;
import com.example.ASM.dto.response.order.OrderStatusResponse;
import com.example.ASM.entity.OrderStatus;

@Mapper(componentModel = "spring", uses = OrderMapper.class)
public interface OrderStatusMapper {

    OrderStatus toOrderStatus(OrderStatusRequest request);

    // Ánh xạ từ OrderStatus sang OrderStatusResponse
    @Mapping(target = "id", source = "id") // Ánh xạ id của OrderStatus
    @Mapping(target = "statusName", source = "statusName") // Ánh xạ tên trạng thái đơn hàng
    @Mapping(target = "orders", source = "orders") // Ánh xạ orders thành danh sách OrderResponse
    OrderStatusResponse toOrderStatusResponse(OrderStatus entity);

    void update(@MappingTarget OrderStatus entity, OrderStatusUpdateRequest request);
}
