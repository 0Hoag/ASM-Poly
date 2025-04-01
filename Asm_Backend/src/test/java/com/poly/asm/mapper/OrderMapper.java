package com.poly.asm.mapper;

import java.util.List;

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
        // Giả sử bạn muốn ánh xạ đến URL của ảnh trong Image entity (thay đổi tùy theo yêu cầu)
        return images.stream()
                .map(image -> image.getUrl())  // Giả sử Image có phương thức getUrl() trả về URL ảnh
                .collect(java.util.stream.Collectors.toList());
    }
}
