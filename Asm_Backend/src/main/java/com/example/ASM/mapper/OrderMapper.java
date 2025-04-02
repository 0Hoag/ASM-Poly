package com.example.ASM.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.example.ASM.dto.request.Order.OrderRequest;
import com.example.ASM.dto.request.Order.OrderUpdateRequest;
import com.example.ASM.dto.response.OrderResponse;
import com.example.ASM.entity.*;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "address", source = "address", qualifiedByName = "mapAddress")
    @Mapping(target = "orderStatus", source = "orderStatus", qualifiedByName = "mapOrderStatus")
    @Mapping(target = "user", source = "user", qualifiedByName = "mapUser")
    Order toOrder(OrderRequest request);

    @Mapping(target = "address", source = "address.id")
    @Mapping(target = "orderStatus", source = "orderStatus.statusName")
    @Mapping(target = "user", source = "user.id")
    @Mapping(target = "orderDetails", source = "orderDetails", qualifiedByName = "mapOrderDetails")
    OrderResponse toOrderResponse(Order entity);

    @Named("mapOrderDetails")
    default List<String> mapOrderDetails(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .map(orderDetail -> "Product: " + orderDetail.getProduct().getProductName() + ", Quantity: "
                        + orderDetail.getQuantity())
                .collect(Collectors.toList());
    }

    @Mapping(target = "address", source = "address", qualifiedByName = "mapAddress")
    @Mapping(target = "orderStatus", source = "orderStatus", qualifiedByName = "mapOrderStatus")
    @Mapping(target = "user", source = "user", qualifiedByName = "mapUser")
    void update(@MappingTarget Order entity, OrderUpdateRequest request);

    @Named("mapAddress")
    default Address mapAddress(int addressId) {
        Address address = new Address();
        address.setId(addressId);
        return address;
    }

    @Named("mapOrderStatus")
    default OrderStatus mapOrderStatus(int statusId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(statusId);
        return orderStatus;
    }

    @Named("mapUser")
    default User mapUser(int userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }
}
