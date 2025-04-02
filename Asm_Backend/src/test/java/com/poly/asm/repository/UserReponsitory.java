package com.poly.asm.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.asm.entity.User;

public interface UserReponsitory extends JpaRepository<User, Integer>{
	Page<User> findByFullNameContainingOrEmailOrPhoneNumber(String name,String email, String phone,Pageable pageable);
	
	Boolean existsByEmailOrPhoneNumber(String email, String phone);
	
	User findByEmailOrPhoneNumber(String email, String phone);
	
	
}
