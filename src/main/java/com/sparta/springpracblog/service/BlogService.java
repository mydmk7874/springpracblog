package com.sparta.springpracblog.service;


import com.sparta.springpracblog.dto.BlogListResponseDto;
import com.sparta.springpracblog.dto.BlogRequestDto;
import com.sparta.springpracblog.dto.BlogResponseDto;
import com.sparta.springpracblog.entity.Blog;
import com.sparta.springpracblog.entity.User;
import com.sparta.springpracblog.jwt.JwtUtil;
import com.sparta.springpracblog.repository.BlogRepository;
import com.sparta.springpracblog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Transactional
    public BlogResponseDto createBlog(BlogRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("잘못된 토큰입니다.");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Blog blog = new Blog(requestDto, user.getUsername());
            blogRepository.save(blog);

            return new BlogResponseDto(blog);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public BlogListResponseDto getBlogs() {
        BlogListResponseDto responseDto = new BlogListResponseDto();

        List<Blog> blogList = blogRepository.findAllByOrderByCreatedAtDesc();

        for(Blog blog : blogList) {
            responseDto.addBlog(new BlogResponseDto(blog));
        }

        return responseDto;
    }

//    @Transactional(readOnly = true)
//    public Optional<Blog> getBlog(Long id) {
//        return blogRepository.findById(id);
//    } 
//    Optional은 고정값이 아니므로 서버에서 Optional로 다루면 안됨 (컴퓨터보고 값을 선택하라는 식의 명령은 피하자)
//    findById()는 리턴값이 Optional이라서 그대로 쓰지 말고 예외처리로 null일 경우를 처리해줘야한다

    @Transactional(readOnly = true)
    public BlogResponseDto getBlog(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        return new BlogResponseDto(blog);
    }

    @Transactional
    public BlogResponseDto update(Long id, BlogRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("잘못된 토큰입니다.");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Blog blog = blogRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 글입니다.")
            );

            if(!blog.getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("해당 사용자가 작성한 글이 아닙니다.");
            }

            blog.update(requestDto);
            return new BlogResponseDto(blog);
        } else {
            return null;
        }
    }

    @Transactional
    public ResponseEntity<String> deleteBlog(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("잘못된 토큰입니다.");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Blog blog = blogRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 글입니다.")
            );

            if(!blog.getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("해당 사용자가 작성한 글이 아닙니다.");
            }
            blogRepository.deleteById(id);
            return new ResponseEntity<>("성공", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
        }
    }

}
