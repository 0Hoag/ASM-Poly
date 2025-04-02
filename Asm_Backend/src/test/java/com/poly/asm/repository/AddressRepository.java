package com.poly.asm.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.poly.asm.entity.Address;

import jakarta.transaction.Transactional;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	Page<Address> findByUserId(int id, Pageable pageable);
	
	@Modifying
    @Transactional
    @Query("UPDATE Address a SET a.defaultAddress = false WHERE a.user.id = ?1")
	void updateAllToNonDefault(int userid);
}
