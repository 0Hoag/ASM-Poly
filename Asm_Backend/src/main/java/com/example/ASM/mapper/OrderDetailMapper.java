package com.example.ASM.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.ASM.dto.request.OrderDetail.OrderDetailRequest;
import com.example.ASM.dto.response.order.OrderDetailResponse;
import com.example.ASM.entity.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    @Mapping(target = "productName", source = "product.productName") // Lấy tên sản phẩm
    @Mapping(target = "orderId", source = "order.id") // Lấy ID đơn hàng
    @Mapping(target = "id", source = "id") // Lấy ID đơn hàng
    OrderDetailResponse toOrderDetailResponse(OrderDetail entity);

    @Mapping(target = "product.id", source = "productId")
    OrderDetail toOrderDetail(OrderDetailRequest request);
}
