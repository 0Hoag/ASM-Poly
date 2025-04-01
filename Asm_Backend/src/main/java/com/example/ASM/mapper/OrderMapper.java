package com.example.ASM.mapper;

import com.example.ASM.dto.request.Order.OrderRequest;
import com.example.ASM.dto.request.Order.OrderUpdateRequest;
import com.example.ASM.dto.response.OrderResponse;
import com.example.ASM.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	Order toOrder(OrderRequest request);

    OrderResponse toOrderResponse(Order entity);

    void update(@MappingTarget Order entity, OrderUpdateRequest request);
}
