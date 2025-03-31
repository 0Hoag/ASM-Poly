package com.poly.asm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.asm.dto.request.address.AddressRequest;
import com.poly.asm.dto.request.user.UserRequest;
import com.poly.asm.dto.response.address.AddressResponse;
import com.poly.asm.dto.response.order.OrderResponse;
import com.poly.asm.dto.response.product.ProductResponse;
import com.poly.asm.dto.response.user.UserResponse;
import com.poly.asm.entity.User;
import com.poly.asm.service.Impl.AddressServiceImpl;
import com.poly.asm.service.Impl.FavoriteServiceImpl;
import com.poly.asm.service.Impl.OrderServiceImpl;
import com.poly.asm.service.Impl.UserServiceImpl;

@RestController
@RequestMapping("/User")
public class HelloWorld {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	FavoriteServiceImpl favoriteService;
	@Autowired
	OrderServiceImpl orderService;
	@Autowired
	AddressServiceImpl addressService;
    @PostMapping("/Create")
    public User create(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }
    @PostMapping("/Update/{userId}")
    public User update(@PathVariable int userId,@RequestBody UserRequest request) {
    	return userService.updateUser(userId, request);
    }
    @GetMapping("/Search")
    public List<UserResponse> search(@RequestParam String keyword) {
    	return userService.searchUser(keyword);
    }
    
    @GetMapping("/{userId}")
    public List<ProductResponse> getFavoriteProducts(@PathVariable int userId) {
        return favoriteService.getFavoriteUser(userId);
    }
    @PostMapping("/toggle")
    public ResponseEntity<String> toggleLike(
            @RequestParam int userId, 
            @RequestParam int productId) {
        String message = favoriteService.toggleLike(userId, productId);
        return ResponseEntity.ok(message);
    }
    @GetMapping("/order/{userId}")
    public List<OrderResponse> getOrdersByUserId(@PathVariable int userId) {
        return orderService.getOrdersByUserId(userId);
    }
    @GetMapping("/address/{userId}")
    public List<AddressResponse> getAddressByUserId(@PathVariable int userId) {
    	return addressService.getAddressByUserId(userId);
    }
    @GetMapping("/default/{addressId}")
    public AddressResponse getIsDefaultAddress(@PathVariable int addressId) {
        return addressService.setDefault(addressId);
    }
    @PostMapping("/createAd/{userId}")
    public ResponseEntity<AddressResponse> createAddress(@PathVariable int userId, @RequestBody AddressRequest request) {
        AddressResponse address = addressService.createAddressByUserId(userId, request);
        return ResponseEntity.ok(address);
    }	
    @PostMapping("/update/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(@RequestBody AddressRequest request,@PathVariable int addressId) {
    	AddressResponse address = addressService.updateAddressById(addressId, request);
    	return ResponseEntity.ok(address);
    }	
}
