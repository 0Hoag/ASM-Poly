package com.example.ASM.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.User.PasswordRequest;
import com.example.ASM.dto.request.User.UpdateUserRequest;
import com.example.ASM.dto.request.User.UserRequest;
import com.example.ASM.dto.response.user.UserResponse;
import com.example.ASM.entity.User;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.UserMapper;
import com.example.ASM.repository.UserRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<UserResponse> List() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public Boolean Create(UserRequest request) {
        if ((request.getEmail() == null || request.getEmail().trim().isEmpty())
                && (request.getPhoneNumber() == null
                        || request.getPhoneNumber().trim().isEmpty())) {
            throw new AppException(ErrorCode.EMAIL_OR_PHONE_REQUIRED);
        }
        if (userRepository.existsByEmailOrPhoneNumber(request.getEmail(), request.getPhoneNumber())) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }

        var user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        return true;
    }

    public PageResponse<UserResponse> Get(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        var pageData = userRepository.findAll(pageable);

        var data =
                pageData.getContent().stream().map(userMapper::toUserResponse).collect(Collectors.toList());

        return PageResponse.<UserResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .data(data)
                .build();
    }

    public PageResponse<UserResponse> Search(String keyword, int page, int size) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        Pageable pageable = PageRequest.of(page - 1, size);

        var pageData = userRepository.findByFullNameContainingOrEmailOrPhoneNumber(keyword, keyword, keyword, pageable);
        var data =
                pageData.getContent().stream().map(userMapper::toUserResponse).collect(Collectors.toList());

        return PageResponse.<UserResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .data(data)
                .build();
    }

    public UserResponse findUserId(int id) {
        var user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    public UserResponse Update(int userId, UpdateUserRequest request) {
        var user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void Delete(int id) {
        if (!userRepository.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        userRepository.deleteById(id);
    }

    public UserResponse changePassword(int userId, PasswordRequest request) {
        var user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.PASSWORD_INCORRECT);
        }

        if (!request.getNewPass().equals(request.getConfirmPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        user.setPassword(passwordEncoder.encode(request.getNewPass()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }
}
