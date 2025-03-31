package com.poly.asm.service;


import com.poly.asm.dto.request.user.UserRequest;
import com.poly.asm.entity.User;

public interface AccountService {
	User getLogin(String account, String password);
	User getRegister(UserRequest request, String comfirmPass);
	User getFogotPass(String account);
}
