package com.sparta.springpracblog.dto;


import com.sparta.springpracblog.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {
    private String comment;
    private LocalDateTime createdAt;
    private String username;

    public CommentResponseDto(Comment comment) {
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
        this.username = comment.getUser().getUsername();
    }

}
