package com.penjualan.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmationValidator implements ConstraintValidator<PasswordConfirmation, Object>{


	private String passwordField;
	private String passwordConfirmationField;

	@Override
	public void initialize(PasswordConfirmation constraintAnnotation) {
		this.passwordField = constraintAnnotation.passwordField();
		this.passwordConfirmationField = constraintAnnotation.passwordConfirmationField();
	}

	public static boolean cekPassword(String passwordField, String passwordConfirmationField){
		boolean cek = true;
		if(passwordField.equals(passwordConfirmationField)){
			cek = false;
		}
		else{
			cek = true;
		}
		return cek;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String passwordValue = new BeanWrapperImpl(value).getPropertyValue(passwordField).toString();
		String passwordConfirmationValue = new BeanWrapperImpl(value).getPropertyValue(passwordConfirmationField).toString();
		return !cekPassword(passwordValue, passwordConfirmationValue);
	}
}
