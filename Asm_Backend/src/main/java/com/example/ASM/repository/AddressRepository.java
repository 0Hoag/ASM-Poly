package com.example.ASM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ASM.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {}
