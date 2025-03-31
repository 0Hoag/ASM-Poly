package com.poly.asm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.poly.asm.entity.Address;

import jakarta.transaction.Transactional;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	List<Address> findByUserId(int id);
	
	@Modifying
    @Transactional
    @Query("UPDATE Address a SET a.defaultAddress = false WHERE a.user.id = ?1")
	void updateAllToNonDefault(int userid);
}
