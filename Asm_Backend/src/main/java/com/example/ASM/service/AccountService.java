package com.example.ASM.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ASM.dto.request.User.UserRequest;
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
public class AccountService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User getLogin(String account, String password) {
        if (account == null || account.isEmpty() || password == null || password.isEmpty())
            throw new AppException(ErrorCode.ACCOUNT_OR_PASSWORD_EMPTY);

        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmailOrPhoneNumber(account, account));
        var user = optionalUser.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        if (!password.equals(user.getPassword())) throw new AppException(ErrorCode.PASSWORD_INCORRECT);

        return user;
    }

    public Boolean getRegister(UserRequest request, String comfirmPass) {
        if (userRepository.findByEmailOrPhoneNumber(request.getEmail(), request.getPassword()) != null)
            throw new AppException(ErrorCode.USER_EXISTS);
        if (comfirmPass.equals(request.getPassword())) {
            throw new AppException(ErrorCode.PASSWORD_INCORRECT);
        }
        var userNew = userMapper.toUser(request);
        userNew.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userNew.setRole(false);
        userRepository.save(userNew);
        return true;
    }
}
