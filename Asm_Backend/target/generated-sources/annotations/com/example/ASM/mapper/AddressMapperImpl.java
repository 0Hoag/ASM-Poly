package com.example.ASM.mapper;

import com.example.ASM.dto.request.Address.AddressRequest;
import com.example.ASM.dto.response.user.AddressResponse;
import com.example.ASM.entity.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressResponse toAddressResponse(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressResponse.AddressResponseBuilder addressResponse = AddressResponse.builder();

        addressResponse.defaultAddress( address.isDefaultAddress() );
        addressResponse.id( address.getId() );
        addressResponse.address( address.getAddress() );
        addressResponse.phoneNumber( address.getPhoneNumber() );
        addressResponse.username( address.getUsername() );

        return addressResponse.build();
    }

    @Override
    public Address toAddress(AddressRequest request) {
        if ( request == null ) {
            return null;
        }

        Address address = new Address();

        address.setAddress( request.getAddress() );
        address.setPhoneNumber( request.getPhoneNumber() );
        address.setUsername( request.getUsername() );

        return address;
    }
}
