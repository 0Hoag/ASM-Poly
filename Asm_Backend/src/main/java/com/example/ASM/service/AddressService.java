package com.example.ASM.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.Address.AddressRequest;
import com.example.ASM.dto.response.user.AddressResponse;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.AddressMapper;
import com.example.ASM.repository.AddressRepository;
import com.example.ASM.repository.UserRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressService {
    AddressRepository addressRepository;
    UserRepository userRepository;
    AddressMapper addressMapper;

    public AddressResponse Create(AddressRequest request) {
        var user = userRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        addressRepository.updateAllToNonDefault(request.getUserId());

        var address = addressMapper.toAddress(request);
        address.setUser(user);
        address.setDefaultAddress(true);

        addressRepository.save(address);

        return addressMapper.toAddressResponse(address);
    }

    public PageResponse<AddressResponse> Get(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
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
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = addressRepository.findByUserId(userId, pageable);

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
        var address = addressRepository
                .findById(addressId)
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

    public AddressResponse Update(int addressId, AddressRequest request) {
        var address =
                addressRepository.findById(addressId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (request.getAddress() != null) {
            address.setAddress(request.getAddress());
        }
        if (request.getPhoneNumber() != null) {
            address.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getUsername() != null) {
            address.setUsername(request.getUsername());
        }
        addressRepository.save(address);
        return addressMapper.toAddressResponse(address);
    }
}
