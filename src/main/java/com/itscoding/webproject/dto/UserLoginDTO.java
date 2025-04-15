package com.itscoding.webproject.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginDTO {
    private String email;
    private String password;
}