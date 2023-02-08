package com.sparta.springpracblog.controller;

import com.sparta.springpracblog.dto.BlogRequestDto;
import com.sparta.springpracblog.entity.Blog;
import com.sparta.springpracblog.repository.BlogRepository;
import com.sparta.springpracblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/api/posts")
    public List<Blog> getBlogs() {
        return blogService.getBlogs();
    }

    @PostMapping("/api/posts")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto) {
        return blogService.createBlog(requestDto);
    }

//    @GetMapping("/api/posts/{id}")
//    public Optional<Blog> getBlog(@PathVariable Long id) {
//        return blogService.getBlog(id);
//    }
//    Optional 쓰지말자

    @GetMapping("/api/posts/{id}")
    public Blog getBlog(@PathVariable Long id) {
        return blogService.getBlog(id);
    }

    @PutMapping("/api/posts/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        return blogService.update(id, requestDto);
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deleteBlog(@PathVariable Long id, @RequestBody String password) {
        return blogService.deleteBlog(id,password);
    }

//    @GetMapping("/api/apitest")
//    public ResponseEntity responseEntity() {
//        return new ResponseEntity("실패", HttpStatus.BAD_REQUEST);
//    }

}
