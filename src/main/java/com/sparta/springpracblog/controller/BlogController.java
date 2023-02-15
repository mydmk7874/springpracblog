package com.sparta.springpracblog.controller;

import com.sparta.springpracblog.dto.BlogListResponseDto;
import com.sparta.springpracblog.dto.BlogRequestDto;
import com.sparta.springpracblog.dto.BlogResponseDto;
import com.sparta.springpracblog.entity.Blog;
import com.sparta.springpracblog.repository.BlogRepository;
import com.sparta.springpracblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/api/posts")
    public BlogListResponseDto getBlogs() {
        return blogService.getBlogs();
    }

    @PostMapping("/api/posts")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto, HttpServletRequest request) {
        return blogService.createBlog(requestDto, request);
    }

//    @GetMapping("/api/posts/{id}")
//    public Optional<Blog> getBlog(@PathVariable Long id) {
//        return blogService.getBlog(id);
//    }
//    Optional 쓰지말자

    @GetMapping("/api/posts/{id}")
    public BlogResponseDto getBlog(@PathVariable Long id) {
        return blogService.getBlog(id);
    }

    @PutMapping("/api/posts/{id}")
    public BlogResponseDto updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto, HttpServletRequest request) {
        return blogService.update(id, requestDto, request);
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity deleteBlog(@PathVariable Long id, HttpServletRequest request) {
        return blogService.deleteBlog(id,request);
    }

//    @GetMapping("/api/apitest")
//    public ResponseEntity responseEntity() {
//        return new ResponseEntity("실패", HttpStatus.BAD_REQUEST);
//    }

}
