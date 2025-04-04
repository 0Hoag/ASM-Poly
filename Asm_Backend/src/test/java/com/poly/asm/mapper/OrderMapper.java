package com.poly.asm.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.poly.asm.dto.response.order.OrderDetailResponse;
import com.poly.asm.dto.response.order.OrderResponse;
import com.poly.asm.entity.Order;
import com.poly.asm.entity.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	
	@Mapping(source = "orderDetails", target = "images" ,qualifiedByName = "mapProductImages") 
    OrderResponse toOrderResponse(Order order);
    
    
	@Named("mapProductImages")
    default List<String> mapProductImages(List<OrderDetail> orderDetails) {
        if (orderDetails == null) {
            return null;
        }
        return orderDetails.stream()
            .flatMap(orderDetail -> orderDetail.getProduct().getImages().stream()) 
            .map(image -> image.getUrl())
            .collect(Collectors.toList());
	}
    
    default OrderDetailResponse map(OrderDetail orderDetail) {
        if (orderDetail == null) {
            return null;
        }
        return OrderDetailResponse.builder()
                .currentPrice(orderDetail.getCurrentPrice())
                .quantity(orderDetail.getQuantity())
                .productId(orderDetail.getProduct().getId()) 
                .build();
    }
}
