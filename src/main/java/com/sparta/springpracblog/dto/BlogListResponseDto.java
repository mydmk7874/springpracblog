package com.sparta.springpracblog.dto;

import com.sparta.springpracblog.entity.Blog;

import java.util.List;

public class BlogListResponseDto {
    private List<BlogResponseDto> blogList;

    public void addBlog(BlogResponseDto responseDto) {
        blogList.add(responseDto);
    }
}
