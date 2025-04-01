package com.example.ASM.mapper;

import com.example.ASM.dto.request.CartDetail.CartDetailRequest;
import com.example.ASM.dto.request.OrderStatus.OrderStatusRequest;
import com.example.ASM.dto.request.OrderStatus.OrderStatusUpdateRequest;
import com.example.ASM.dto.response.CartDetailResponse;
import com.example.ASM.dto.response.OrderStatusResponse;
import com.example.ASM.entity.Cart;
import com.example.ASM.entity.CartDetail;
import com.example.ASM.entity.OrderStatus;
import com.example.ASM.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {
	OrderStatus toOrderStatus(OrderStatusRequest request);

    OrderStatusResponse toOrderStatusResponse(OrderStatus entity);

    void update(@MappingTarget OrderStatus entity, OrderStatusUpdateRequest request);
}
