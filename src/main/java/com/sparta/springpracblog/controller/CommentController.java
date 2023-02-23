package com.sparta.springpracblog.controller;

import com.sparta.springpracblog.dto.CommentRequestDto;
import com.sparta.springpracblog.dto.CommentResponseDto;
import com.sparta.springpracblog.security.UserDetailsImpl;
import com.sparta.springpracblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/posts/{id}")
    public CommentResponseDto createComment(@PathVariable Long id,
                                            @RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(id, requestDto, userDetails.getUser());
    }

    @PutMapping("/api/posts/{blogId}/{commentId}")
    public CommentResponseDto update(@PathVariable Long blogId, @PathVariable Long commentId,
                                     @RequestBody CommentRequestDto requestDto,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.update(blogId, commentId, requestDto, userDetails.getUser());
    }

    @DeleteMapping("/api/posts/{blogId}/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long blogId, @PathVariable Long commentId,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(blogId, commentId, userDetails.getUser());
    }
}
