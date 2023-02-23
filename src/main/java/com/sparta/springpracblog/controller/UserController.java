package com.sparta.springpracblog.controller;


import com.sparta.springpracblog.dto.LoginRequestDto;
import com.sparta.springpracblog.dto.SignupRequestDto;
import com.sparta.springpracblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return "success";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse) {
        userService.login(loginRequestDto, httpServletResponse);
        return "success";
    }


}
