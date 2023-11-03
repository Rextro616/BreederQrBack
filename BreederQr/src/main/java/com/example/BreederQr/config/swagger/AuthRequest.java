package com.example.BreederQr.config.swagger;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Data
public class AuthRequest {
    @NotNull @Email @Length(min = 5, max = 50)
    private String email;

    @NotNull @Length(min = 5, max = 10)
    private String password;

}