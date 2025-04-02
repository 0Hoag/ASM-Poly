package com.poly.asm.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.poly.asm.dto.request.user.UserRequest;
import com.poly.asm.entity.User;
import com.poly.asm.exception.AppException;
import com.poly.asm.exception.ErrorCode;
import com.poly.asm.mapper.UserMapper;
import com.poly.asm.repository.UserReponsitory;
import com.poly.asm.service.AccountService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
	UserReponsitory userReponsitory;
	UserMapper userMapper;

	public User getLogin(String account, String password) {
		if (account == null || account.isEmpty() || password == null || password.isEmpty()) 
			throw new AppException(ErrorCode.ACCOUNT_OR_PASSWORD_EMPTY);
		
		Optional<User> optionalUser = Optional.ofNullable(userReponsitory.findByEmailOrPhoneNumber(account, account));
		var user = optionalUser.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
		if (!password.equals(user.getPassword()))
			throw new AppException(ErrorCode.PASSWORD_INCORRECT);

		return user;
	}

	public Boolean getRegister(UserRequest request,String comfirmPass) {
		if (userReponsitory.findByEmailOrPhoneNumber(request.getEmail(),request.getPassword()) != null)
			 throw new AppException(ErrorCode.USER_EXITSTED);
		if (comfirmPass.equals(request.getPassword())) {
			throw new AppException(ErrorCode.PASSWORD_INCORRECT);
		}
		 var userNew = userMapper.toUser(request);
		 userNew.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		 userNew.setRole(false);
		 userReponsitory.save(userNew);
		return true;
	}


}
