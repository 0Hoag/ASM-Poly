package com.example.ASM.mapper;

import com.example.ASM.dto.response.order.OrderDetailResponse;
import com.example.ASM.entity.Order;
import com.example.ASM.entity.OrderDetail;
import com.example.ASM.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class OrderDetailMapperImpl implements OrderDetailMapper {

    @Override
    public OrderDetailResponse toOrderDetailResponse(OrderDetail entity) {
        if ( entity == null ) {
            return null;
        }

        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();

        orderDetailResponse.setProductName( entityProductProductName( entity ) );
        orderDetailResponse.setOrderId( entityOrderId( entity ) );
        orderDetailResponse.setCurrentPrice( entity.getCurrentPrice() );
        orderDetailResponse.setQuantity( entity.getQuantity() );

        return orderDetailResponse;
    }

    private String entityProductProductName(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }
        Product product = orderDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        String productName = product.getProductName();
        if ( productName == null ) {
            return null;
        }
        return productName;
    }

    private int entityOrderId(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return 0;
        }
        Order order = orderDetail.getOrder();
        if ( order == null ) {
            return 0;
        }
        int id = order.getId();
        return id;
    }
}
