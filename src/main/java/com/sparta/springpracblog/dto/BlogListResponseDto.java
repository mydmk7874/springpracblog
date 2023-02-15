package com.sparta.springpracblog.dto;

import com.sparta.springpracblog.entity.Blog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class BlogListResponseDto {
    private List<BlogResponseDto> blogList = new ArrayList<>();

    public void addBlog(BlogResponseDto responseDto) {
        blogList.add(responseDto);
    }
}
