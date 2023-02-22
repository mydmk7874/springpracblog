package com.sparta.springpracblog.entity;

import com.sparta.springpracblog.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "BLOG_ID", nullable = false)
    private Blog blog;

    public Comment(String comment, User user, Blog blog) {
        this.comment = comment;
        this.user = user;
        this.blog = blog;
    }

    public Comment(CommentRequestDto commentRequestDto, User user, Blog blog) {
        this.comment = commentRequestDto.getComment();
        this.user = user;
        this.blog = blog;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}
