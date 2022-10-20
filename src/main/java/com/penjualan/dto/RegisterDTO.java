package com.penjualan.dto;


import com.penjualan.validation.PasswordConfirmation;
import com.penjualan.validation.UniqueUsername;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@PasswordConfirmation(message = "The password and the confirmation password are not the same.",
passwordField = "password", passwordConfirmationField = "passwordConfirmation")
public class RegisterDTO {
    @UniqueUsername(message="Username is already exist within the database")
    @NotBlank(message = "Username is required")
    @Size(max = 20, message = "Username can't be more than 20 characters.")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(max = 20, message = "Password can't be more than 20 characters.")
    private String password;
    @NotBlank(message = "Password confirmation is required")
    @Size(max = 20, message = "Password confirmation can't be more tha 20 characters.")
    private String passwordConfirmation;





}
