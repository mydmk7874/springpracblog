package com.sparta.springpracblog.dto;


import lombok.Getter;

@Getter
public class BlogRequestDto {
    private String title;
    private String author;
    private String password;
    private String content;
}
