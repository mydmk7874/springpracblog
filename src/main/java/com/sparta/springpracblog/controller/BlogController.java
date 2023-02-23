package com.sparta.springpracblog.controller;

import com.sparta.springpracblog.dto.BlogListResponseDto;
import com.sparta.springpracblog.dto.BlogRequestDto;
import com.sparta.springpracblog.dto.BlogResponseDto;
import com.sparta.springpracblog.entity.Blog;
import com.sparta.springpracblog.repository.BlogRepository;
import com.sparta.springpracblog.security.UserDetailsImpl;
import com.sparta.springpracblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return blogService.createBlog(requestDto, userDetails.getUser());
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
    public BlogResponseDto updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return blogService.update(id, requestDto, userDetails.getUser());
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return blogService.deleteBlog(id,userDetails.getUser());
    }
    //ResponseEntity<T> <T> 가능하면 꼭 쓰자

//    @GetMapping("/api/apitest")
//    public ResponseEntity responseEntity() {
//        return new ResponseEntity("실패", HttpStatus.BAD_REQUEST);
//    }

}
