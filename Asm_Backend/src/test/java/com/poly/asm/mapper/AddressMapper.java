package com.poly.asm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.poly.asm.dto.request.address.AddressRequest;
import com.poly.asm.dto.response.address.AddressResponse;
import com.poly.asm.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(source = "user.fullName", target = "userName")
	@Mapping(source = "defaultAddress", target = "defaultAddress")
    AddressResponse toAddressResponse(Address address);
    
	@Mapping(target = "defaultAddress", ignore = true)
    Address toAddress(AddressRequest request);
}
