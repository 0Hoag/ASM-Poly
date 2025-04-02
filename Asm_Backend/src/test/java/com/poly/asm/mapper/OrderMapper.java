package com.poly.asm.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.poly.asm.dto.response.order.OrderResponse;
import com.poly.asm.entity.Image;
import com.poly.asm.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponse toOrderResponse(Order order);
    
    default List<String> map(List<Image> images) {
        if (images == null) {
            return null;
        }
        return images.stream()
                .map(image -> image.getUrl())  
                .collect(Collectors.toList());
    }
}
