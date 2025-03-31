package com.poly.asm.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm.dto.request.address.AddressRequest;
import com.poly.asm.dto.response.address.AddressResponse;
import com.poly.asm.entity.Address;
import com.poly.asm.entity.User;
import com.poly.asm.mapper.AddressMapper;
import com.poly.asm.repository.AddressRepository;
import com.poly.asm.repository.UserReponsitory;
import com.poly.asm.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserReponsitory userReponsitory;
	@Autowired
	AddressMapper addressMapper;
	
	@Override
	public List<AddressResponse> getAddressByUserId(int userId) {
		List<Address> address = addressRepository.findByUserId(userId);
		return address.stream()
				.map(addressMapper::toAddressResponse)
				.collect(Collectors.toList());
	}
	
	@Override
	public AddressResponse setDefault(int addressId) {
		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy địa chỉ"));
		
		addressRepository.updateAllToNonDefault(address.getUser().getId());
		address.setDefaultAddress(true);
		addressRepository.save(address);
		return addressMapper.toAddressResponse(address);
	}
	
	@Override
	public void deleteAddress(int addressId) {
		addressRepository.deleteById(addressId);
	}

	@Override
	public AddressResponse createAddressByUserId(int userId, AddressRequest request) {
		User user = userReponsitory.findById(userId)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy User"));
		addressRepository.updateAllToNonDefault(userId);
		Address address = addressMapper.toAddress(request);
		address.setUser(user);
		address.setDefaultAddress(true);
		addressRepository.save(address);
		return addressMapper.toAddressResponse(address);
	}

	@Override
	public AddressResponse updateAddressById(int addressId, AddressRequest request) {
		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy Địa chỉ"));
		
		if (request.getAddress() != null) {
	        address.setAddress(request.getAddress());
	    }
	    if (request.getPhoneNumber() != null) {
	        address.setPhoneNumber(request.getPhoneNumber());
	    }
	    if (request.getUserName() != null) {
	        address.setUserName(request.getUserName());
	    }
		addressRepository.save(address);
		return addressMapper.toAddressResponse(address);
	}

}
