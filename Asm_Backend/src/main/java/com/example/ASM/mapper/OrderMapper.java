package com.example.ASM.mapper;

import com.example.ASM.dto.request.Order.OrderRequest;
import com.example.ASM.dto.response.OrderResponse;
import com.example.ASM.dto.response.OrderDetailResponse;
import com.example.ASM.entity.Order;
import com.example.ASM.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {OrderDetailMapper.class})
public abstract class OrderMapper {


    @Autowired
    protected OrderDetailMapper orderDetailMapper;

    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUser")
    public abstract Order toOrder(OrderRequest request);

    @Mapping(target = "status", source = "user", qualifiedByName = "mapUserName")  // Thay 'userName' thành 'status'
    @Mapping(target = "orderDetails", expression = "java(mapOrderDetails(entity))")
    public abstract OrderResponse toOrderResponse(Order entity);

    protected List<OrderDetailResponse> mapOrderDetails(Order order) {
        return order.getOrderDetails().stream()
                .map(orderDetailMapper::toOrderDetailResponse)
                .collect(Collectors.toList());
    }

    @Named("mapUser")
    protected User mapUser(Integer userId) {
        if (userId == null) return null;
        User userEntity = new User();
        userEntity.setId(userId);
        return userEntity;
    }

    @Named("mapUserName")
    protected String mapUserName(User user) {
        return (user != null) ? user.getFullName() : null;
    }
}
