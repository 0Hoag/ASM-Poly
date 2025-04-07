package com.example.ASM.mapper;

import com.example.ASM.dto.request.Address.AddressRequest;
import com.example.ASM.dto.response.user.AddressResponse;
import com.example.ASM.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	@Mapping(source = "defaultAddress", target = "defaultAddress")
    AddressResponse toAddressResponse(Address address);
    
	@Mapping(target = "defaultAddress", ignore = true)
    Address toAddress(AddressRequest request);
}
