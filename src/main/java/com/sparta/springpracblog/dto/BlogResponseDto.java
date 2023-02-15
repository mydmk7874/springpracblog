package com.sparta.springpracblog.dto;

import com.sparta.springpracblog.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogResponseDto {
    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;

    public BlogResponseDto(Blog blog) {
        this.title = blog.getTitle();
        this.username = blog.getUsername();
        this.content = blog.getContent();
        this.createdAt = blog.getCreatedAt();
    }

}
