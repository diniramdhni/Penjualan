package com.penjualan.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordConfirmationValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConfirmation {
	
	public Class<?>[] groups() default {};
	public Class<? extends Payload>[] payload() default {};
	public String message();
	public String passwordField();
	public String passwordConfirmationField();
}
