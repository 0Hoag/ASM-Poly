package com.example.ASM.mapper;

import com.example.ASM.dto.request.Order.OrderRequest;
import com.example.ASM.dto.request.Order.OrderUpdateRequest;
import com.example.ASM.dto.response.order.OrderResponse;
import com.example.ASM.entity.Address;
import com.example.ASM.entity.Order;
import com.example.ASM.entity.OrderStatus;
import com.example.ASM.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toOrder(OrderRequest request) {
        if ( request == null ) {
            return null;
        }

        Order order = new Order();

        order.setAddress( mapAddress( request.getAddress() ) );
        order.setOrderStatus( mapOrderStatus( request.getOrderStatus() ) );
        order.setUser( mapUser( request.getUser() ) );
        order.setTotalAmount( request.getTotalAmount() );

        return order;
    }

    @Override
    public OrderResponse toOrderResponse(Order entity) {
        if ( entity == null ) {
            return null;
        }

        OrderResponse.OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.address( entityAddressId( entity ) );
        orderResponse.orderStatus( entityOrderStatusStatusName( entity ) );
        orderResponse.user( entityUserId( entity ) );
        orderResponse.orderDetails( mapOrderDetails( entity.getOrderDetails() ) );
        orderResponse.id( entity.getId() );
        orderResponse.createdAt( entity.getCreatedAt() );
        orderResponse.totalAmount( entity.getTotalAmount() );

        return orderResponse.build();
    }

    @Override
    public void update(Order entity, OrderUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        entity.setAddress( mapAddress( request.getAddress() ) );
        entity.setOrderStatus( mapOrderStatus( request.getOrderStatus() ) );
        entity.setUser( mapUser( request.getUser() ) );
        entity.setTotalAmount( request.getTotalAmount() );
    }

    private int entityAddressId(Order order) {
        if ( order == null ) {
            return 0;
        }
        Address address = order.getAddress();
        if ( address == null ) {
            return 0;
        }
        int id = address.getId();
        return id;
    }

    private String entityOrderStatusStatusName(Order order) {
        if ( order == null ) {
            return null;
        }
        OrderStatus orderStatus = order.getOrderStatus();
        if ( orderStatus == null ) {
            return null;
        }
        String statusName = orderStatus.getStatusName();
        if ( statusName == null ) {
            return null;
        }
        return statusName;
    }

    private int entityUserId(Order order) {
        if ( order == null ) {
            return 0;
        }
        User user = order.getUser();
        if ( user == null ) {
            return 0;
        }
        int id = user.getId();
        return id;
    }
}
