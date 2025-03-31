package com.poly.asm.service;

import java.util.List;

import com.poly.asm.dto.request.address.AddressRequest;
import com.poly.asm.dto.response.address.AddressResponse;


public interface AddressService {
	List<AddressResponse> getAddressByUserId(int userId);
	AddressResponse createAddressByUserId(int userId,AddressRequest request);
	AddressResponse updateAddressById(int addressId, AddressRequest request);
	AddressResponse setDefault(int addressId);
	void deleteAddress(int id);
}
