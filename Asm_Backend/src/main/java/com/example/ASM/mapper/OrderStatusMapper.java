package com.example.ASM.mapper;

import com.example.ASM.dto.request.OrderStatus.OrderStatusRequest;
import com.example.ASM.dto.request.OrderStatus.OrderStatusUpdateRequest;
import com.example.ASM.dto.response.order.OrderResponse;
import com.example.ASM.dto.response.order.OrderStatusResponse;
import com.example.ASM.entity.Order;
import com.example.ASM.entity.OrderDetail;
import com.example.ASM.entity.OrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {

    OrderStatus toOrderStatus(OrderStatusRequest request);

    // Ánh xạ từ Order sang OrderResponse
    @Mapping(target = "address", source = "address.id")  // Chỉ ánh xạ id của address
    @Mapping(target = "orderStatus", source = "orderStatus.statusName")  // Lấy tên của orderStatus
    @Mapping(target = "user", source = "user.id")  // Chỉ ánh xạ id của user
    @Mapping(target = "orderDetails", source = "orderDetails", qualifiedByName = "mapOrderDetails")  // Ánh xạ chi tiết đơn hàng
    OrderResponse toOrderResponse(Order entity);

    // Ánh xạ từ OrderStatus sang OrderStatusResponse
    @Mapping(target = "id", source = "id")  // Ánh xạ id của OrderStatus
    @Mapping(target = "statusName", source = "statusName")  // Ánh xạ tên trạng thái đơn hàng
    @Mapping(target = "orders", source = "orders", qualifiedByName = "mapOrders")  // Ánh xạ orders thành danh sách OrderResponse
    OrderStatusResponse toOrderStatusResponse(OrderStatus entity);

    // Ánh xạ từ List<Order> thành List<OrderResponse> (Thêm phương thức ánh xạ này)
    @Named("mapOrders")
    default List<OrderResponse> mapOrders(List<Order> orders) {
        return orders.stream()
                .map(this::toOrderResponse)  // Ánh xạ từ Order sang OrderResponse
                .collect(Collectors.toList());
    }

    // Ánh xạ OrderDetails (Nếu cần, thêm ánh xạ cụ thể ở đây)
    @Named("mapOrderDetails")
    default List<String> mapOrderDetails(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .map(orderDetail -> "Product: " + orderDetail.getProduct().getProductName() + ", Quantity: "
                        + orderDetail.getQuantity())
                .collect(Collectors.toList());
    }


    void update(@MappingTarget OrderStatus entity, OrderStatusUpdateRequest request);
}
