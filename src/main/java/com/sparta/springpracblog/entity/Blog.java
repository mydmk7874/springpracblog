package com.sparta.springpracblog.entity;


import com.sparta.springpracblog.dto.BlogRequestDto;
import com.sparta.springpracblog.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Blog extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

//    @Embedded
//    private User user;
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String content;

    public Blog(String title, String username, String content) {
        this.title = title;
        this.content = content;
        this.username = username;
    }

    public Blog(BlogRequestDto requestDto, String username) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = username;
    }


    public void update(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
