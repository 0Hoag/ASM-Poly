package com.example.ASM.mapper;

import com.example.ASM.dto.request.Cart.CartRequest;
import com.example.ASM.dto.response.CartDetailResponse;
import com.example.ASM.dto.response.CartResponse;
import com.example.ASM.entity.Cart;
import com.example.ASM.entity.CartDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public Cart toCart(CartRequest request) {
        if ( request == null ) {
            return null;
        }

        Cart cart = new Cart();

        cart.setUser( mapUser( request.getUser() ) );

        return cart;
    }

    @Override
    public CartResponse toCartResponse(Cart entity) {
        if ( entity == null ) {
            return null;
        }

        CartResponse.CartResponseBuilder cartResponse = CartResponse.builder();

        cartResponse.userName( mapUserName( entity.getUser() ) );
        cartResponse.id( entity.getId() );
        cartResponse.cartDetails( cartDetailListToCartDetailResponseList( entity.getCartDetails() ) );
        cartResponse.createdAt( entity.getCreatedAt() );

        return cartResponse.build();
    }

    protected CartDetailResponse cartDetailToCartDetailResponse(CartDetail cartDetail) {
        if ( cartDetail == null ) {
            return null;
        }

        CartDetailResponse.CartDetailResponseBuilder cartDetailResponse = CartDetailResponse.builder();

        cartDetailResponse.id( cartDetail.getId() );
        cartDetailResponse.quantity( cartDetail.getQuantity() );

        return cartDetailResponse.build();
    }

    protected List<CartDetailResponse> cartDetailListToCartDetailResponseList(List<CartDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<CartDetailResponse> list1 = new ArrayList<CartDetailResponse>( list.size() );
        for ( CartDetail cartDetail : list ) {
            list1.add( cartDetailToCartDetailResponse( cartDetail ) );
        }

        return list1;
    }
}
