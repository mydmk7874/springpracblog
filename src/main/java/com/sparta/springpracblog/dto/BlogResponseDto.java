package com.sparta.springpracblog.dto;

import com.sparta.springpracblog.entity.Blog;
import com.sparta.springpracblog.entity.Comment;
import com.sparta.springpracblog.entity.User;
import com.sparta.springpracblog.repository.CommentRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class BlogResponseDto {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String username;
    private CommentListResponseDto commentList = new CommentListResponseDto();

    public BlogResponseDto(Blog blog) {
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.createdAt = blog.getCreatedAt();
        this.username = blog.getUser().getUsername();

        List<Comment> comments = blog.getComments();
        Collections.reverse(comments);

        for(Comment c : comments) {
            commentList.addComment(new CommentResponseDto(c));
        }
    }

}
