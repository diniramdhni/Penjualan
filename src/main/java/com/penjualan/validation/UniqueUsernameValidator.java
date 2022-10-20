package com.penjualan.validation;


import com.penjualan.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{
	
	@Autowired
	private LoginService loginService;
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		return !loginService.checkExistingUsername(username);
	}

}
