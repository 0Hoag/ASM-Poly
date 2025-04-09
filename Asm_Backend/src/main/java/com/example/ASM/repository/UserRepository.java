package com.example.ASM.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ASM.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Page<User> findByFullNameContainingOrEmailOrPhoneNumber(String name, String email, String phone, Pageable pageable);

    Optional<User> findByEmail(String email);

    Boolean existsByEmailOrPhoneNumber(String email, String phone);

    User findByEmailOrPhoneNumber(String email, String phone);
}
