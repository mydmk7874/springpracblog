package com.sparta.springpracblog.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private boolean admin = false;
    private String adminToken = "";

}
