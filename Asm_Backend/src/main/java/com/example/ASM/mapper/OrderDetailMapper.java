package com.example.ASM.mapper;

import com.example.ASM.dto.response.OrderDetailResponse;
import com.example.ASM.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    @Mapping(target = "productName", source = "product.productName")  // Ánh xạ productName từ Product
    @Mapping(target = "productId", source = "product.id")  // Ánh xạ productId từ Product
    OrderDetailResponse toOrderDetailResponse(OrderDetail entity);
}
