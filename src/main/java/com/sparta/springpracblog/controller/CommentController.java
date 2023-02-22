package com.sparta.springpracblog.controller;

import com.sparta.springpracblog.dto.CommentRequestDto;
import com.sparta.springpracblog.dto.CommentResponseDto;
import com.sparta.springpracblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/posts/{id}")
    public CommentResponseDto createComment(@PathVariable Long id,
                                            @RequestBody CommentRequestDto requestDto,
                                            HttpServletRequest httpServletRequest) {
        return commentService.createComment(id, requestDto, httpServletRequest);
    }

    @PutMapping("/api/posts/{blogId}/{commentId}")
    public CommentResponseDto update(@PathVariable Long blogId, @PathVariable Long commentId,
                                     @RequestBody CommentRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return commentService.update(blogId, commentId, requestDto, httpServletRequest);
    }

    @DeleteMapping("/api/posts/{blogId}/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long blogId, @PathVariable Long commentId,
                                                HttpServletRequest httpServletRequest) {
        return commentService.deleteComment(blogId, commentId, httpServletRequest);
    }
}
