package com.example.ASM.mapper;

import com.example.ASM.dto.request.CartDetail.CartDetailRequest;
import com.example.ASM.dto.response.CartDetailResponse;
import com.example.ASM.entity.CartDetail;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class CartDetailMapperImpl implements CartDetailMapper {

    @Override
    public CartDetail toCartDetail(CartDetailRequest request) {
        if ( request == null ) {
            return null;
        }

        CartDetail cartDetail = new CartDetail();

        cartDetail.setProduct( mapProduct( request.getProduct() ) );
        cartDetail.setQuantity( request.getQuantity() );

        return cartDetail;
    }

    @Override
    public CartDetailResponse toCartDetailResponse(CartDetail entity) {
        if ( entity == null ) {
            return null;
        }

        CartDetailResponse.CartDetailResponseBuilder cartDetailResponse = CartDetailResponse.builder();

        cartDetailResponse.productName( mapProductName( entity.getProduct() ) );
        cartDetailResponse.id( entity.getId() );
        cartDetailResponse.quantity( entity.getQuantity() );

        return cartDetailResponse.build();
    }
}
