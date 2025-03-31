package com.poly.asm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.asm.entity.User;

public interface UserReponsitory extends JpaRepository<User, Integer>{
	List<User> findByFullNameContainingOrEmailOrPhoneNumber(String name,String email, String phone);
	
	Boolean existsByEmailOrPhoneNumber(String email, String phone);
	
	User findByEmailOrPhoneNumber(String email, String phone);
	
	
}
