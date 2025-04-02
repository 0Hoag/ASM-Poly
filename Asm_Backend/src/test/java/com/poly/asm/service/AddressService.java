package com.poly.asm.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.asm.dto.PageResponse;
import com.poly.asm.dto.request.address.AddressRequest;
import com.poly.asm.dto.response.address.AddressResponse;
import com.poly.asm.exception.AppException;
import com.poly.asm.exception.ErrorCode;
import com.poly.asm.mapper.AddressMapper;
import com.poly.asm.repository.AddressRepository;
import com.poly.asm.repository.UserReponsitory;
import com.poly.asm.service.AddressService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressService{
	AddressRepository addressRepository;
	UserReponsitory userReponsitory;
	AddressMapper addressMapper;
	
	
	
	public PageResponse<AddressResponse> Get( int page, int size) {
		Pageable pageable = PageRequest.of(page -1, size);
		var pageData = addressRepository.findAll(pageable);
		
		var data = pageData.getContent().stream()
				.map(addressMapper::toAddressResponse)
				.collect(Collectors.toList());
		return PageResponse.<AddressResponse>builder()
				.currentPage(page)
				.totalPages(pageData.getTotalPages())
				.pageSize(pageData.getSize())
				.totalElements(pageData.getTotalElements())
				.data(data)
				.build();
	}
	public PageResponse<AddressResponse> getUserId(int userId, int page, int size) {
		Pageable pageable = PageRequest.of(page -1, size);
		var pageData = addressRepository.findByUserId(userId ,pageable);
		
		var data = pageData.getContent().stream()
				.map(addressMapper::toAddressResponse)
				.collect(Collectors.toList());
		return PageResponse.<AddressResponse>builder()
				.currentPage(page)
				.totalPages(pageData.getTotalPages())
				.pageSize(pageData.getSize())
				.totalElements(pageData.getTotalElements())
				.data(data)
				.build();
	}
	
	public AddressResponse setDefault(int addressId) {
		var address = addressRepository.findById(addressId)
				.orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_EXISTED));
		
		addressRepository.updateAllToNonDefault(address.getUser().getId());
		address.setDefaultAddress(true);
		addressRepository.save(address);
		return addressMapper.toAddressResponse(address);
	}
	
	public void Delete(int addressId) {
		if (!addressRepository.existsById(addressId)) {
            throw new AppException(ErrorCode.ADDRESS_NOT_EXISTED);
        }
		addressRepository.deleteById(addressId);
	}

	public AddressResponse createUserId(int userId, AddressRequest request) {
		var user = userReponsitory.findById(userId)
	            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)); 

	    addressRepository.updateAllToNonDefault(userId);

	    var address = addressMapper.toAddress(request);
	    address.setUser(user);
		address.setDefaultAddress(true);
	    
	    addressRepository.save(address);
	    
	    return addressMapper.toAddressResponse(address);
	}

	public AddressResponse Update(int addressId, AddressRequest request) {
		var address = addressRepository.findById(addressId)
				.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
		
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
