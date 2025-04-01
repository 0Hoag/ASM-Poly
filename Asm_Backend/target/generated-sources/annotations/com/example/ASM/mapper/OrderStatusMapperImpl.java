package com.example.ASM.mapper;

import com.example.ASM.dto.request.OrderStatus.OrderStatusRequest;
import com.example.ASM.dto.request.OrderStatus.OrderStatusUpdateRequest;
import com.example.ASM.dto.response.OrderStatusResponse;
import com.example.ASM.entity.Order;
import com.example.ASM.entity.OrderStatus;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class OrderStatusMapperImpl implements OrderStatusMapper {

    @Override
    public OrderStatus toOrderStatus(OrderStatusRequest request) {
        if ( request == null ) {
            return null;
        }

        OrderStatus orderStatus = new OrderStatus();

        orderStatus.setStatusName( request.getStatusName() );

        return orderStatus;
    }

    @Override
    public OrderStatusResponse toOrderStatusResponse(OrderStatus entity) {
        if ( entity == null ) {
            return null;
        }

        OrderStatusResponse.OrderStatusResponseBuilder orderStatusResponse = OrderStatusResponse.builder();

        orderStatusResponse.id( entity.getId() );
        orderStatusResponse.statusName( entity.getStatusName() );
        List<Order> list = entity.getOrders();
        if ( list != null ) {
            orderStatusResponse.orders( new ArrayList<Order>( list ) );
        }

        return orderStatusResponse.build();
    }

    @Override
    public void update(OrderStatus entity, OrderStatusUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        entity.setStatusName( request.getStatusName() );
    }
}
