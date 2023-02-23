package com.sparta.springpracblog.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class LoginRequestDto {
    @Pattern(regexp = "[a-z1-9]{4,10}")
    private String username;
    @Pattern(regexp = "[a-zA-Z1-9`~!@#$%^&*()\\-_=+]{8,15}")
    private String password;

}
