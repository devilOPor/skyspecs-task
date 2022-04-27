package com.skyspecs.task1.dto;

import lombok.Data;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    private int id;
    @NotNull
    @Size(min = 2, message = "name should have at least 2 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;
    @NotNull
    @Email
    private String email;
}

