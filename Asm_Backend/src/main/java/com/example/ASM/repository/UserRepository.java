package com.example.ASM.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ASM.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Tìm người dùng theo email
    Optional<User> findByEmail(String email);

    // Tìm người dùng theo số điện thoại
    Optional<User> findByPhoneNumber(String phoneNumber);

    // Kiểm tra email đã tồn tại chưa
    boolean existsByEmail(String email);

    // Kiểm tra số điện thoại đã tồn tại chưa
    boolean existsByPhoneNumber(String phoneNumber);
}
