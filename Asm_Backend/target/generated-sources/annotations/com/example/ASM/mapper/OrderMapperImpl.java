package com.example.ASM.mapper;

import com.example.ASM.dto.request.Order.OrderRequest;
import com.example.ASM.dto.request.OrderDetail.OrderDetailRequest;
import com.example.ASM.dto.response.OrderResponse;
import com.example.ASM.entity.Order;
import com.example.ASM.entity.OrderDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl extends OrderMapper {

    @Override
    public Order toOrder(OrderRequest request) {
        if ( request == null ) {
            return null;
        }

        Order order = new Order();

        order.setUser( mapUser( request.getUserId() ) );
        order.setTotalAmount( request.getTotalAmount() );
        order.setOrderDetails( orderDetailRequestListToOrderDetailList( request.getOrderDetails() ) );

        return order;
    }

    @Override
    public OrderResponse toOrderResponse(Order entity) {
        if ( entity == null ) {
            return null;
        }

        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setStatus( mapUserName( entity.getUser() ) );
        orderResponse.setId( entity.getId() );
        orderResponse.setCreatedAt( entity.getCreatedAt() );
        orderResponse.setTotalAmount( entity.getTotalAmount() );

        orderResponse.setOrderDetails( mapOrderDetails(entity) );

        return orderResponse;
    }

    protected OrderDetail orderDetailRequestToOrderDetail(OrderDetailRequest orderDetailRequest) {
        if ( orderDetailRequest == null ) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setCurrentPrice( orderDetailRequest.getCurrentPrice() );
        orderDetail.setQuantity( orderDetailRequest.getQuantity() );

        return orderDetail;
    }

    protected List<OrderDetail> orderDetailRequestListToOrderDetailList(List<OrderDetailRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDetail> list1 = new ArrayList<OrderDetail>( list.size() );
        for ( OrderDetailRequest orderDetailRequest : list ) {
            list1.add( orderDetailRequestToOrderDetail( orderDetailRequest ) );
        }

        return list1;
    }
}
