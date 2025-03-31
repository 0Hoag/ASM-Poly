package com.poly.asm.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm.dto.request.user.UserRequest;
import com.poly.asm.entity.User;
import com.poly.asm.mapper.UserMapper;
import com.poly.asm.repository.UserReponsitory;
import com.poly.asm.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	UserReponsitory userReponsitory;
	@Autowired
	UserMapper userMapper;

	@Override
	public User getLogin(String account, String password) {
		if (account == null || account.isEmpty() || password == null || password.isEmpty())
			throw new RuntimeException("Vui lòng nhập tài khoản và mật khẩu");
		
		Optional<User> optionalUser = Optional.ofNullable(userReponsitory.findByEmailOrPhoneNumber(account, account));
		User user = optionalUser.orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại!"));
		if (!password.equals(user.getPassword()))
			throw new RuntimeException("Mật khẩu không chính xác!");

		return user;
	}

	@Override
	public User getRegister(UserRequest request,String comfirmPass) {
		if (userReponsitory.findByEmailOrPhoneNumber(request.getEmail(),request.getPassword()) != null)
			 throw new RuntimeException("Tài khoản đã tồn tại!");
		if (comfirmPass.equals(request.getPassword())) {
			throw new RuntimeException("Xác nhận mật khẩu không khớp");
		}
		 User userNew = userMapper.toUser(request);
		 userNew.setRole(false);
		return userNew;
	}

	@Override
	public User getFogotPass(String account) {
		// TODO Auto-generated method stub
		return null;
	}

}
