package com.example.ASM.repository;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ASM.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Page<Address> findByUserId(int id, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Address a SET a.defaultAddress = false WHERE a.user.id = ?1")
    void updateAllToNonDefault(int userid);
}
