package com.example.ASM.mapper;

import com.example.ASM.dto.response.order.OrderDetailResponse;
import com.example.ASM.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    @Mapping(target = "productName", source = "product.productName")  // Lấy tên sản phẩm
    @Mapping(target = "orderId", source = "order.id")  // Lấy ID đơn hàng
    OrderDetailResponse toOrderDetailResponse(OrderDetail entity);
}
