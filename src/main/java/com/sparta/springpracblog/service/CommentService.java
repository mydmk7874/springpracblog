package com.sparta.springpracblog.service;

import com.sparta.springpracblog.dto.BlogRequestDto;
import com.sparta.springpracblog.dto.BlogResponseDto;
import com.sparta.springpracblog.dto.CommentRequestDto;
import com.sparta.springpracblog.dto.CommentResponseDto;
import com.sparta.springpracblog.entity.Blog;
import com.sparta.springpracblog.entity.Comment;
import com.sparta.springpracblog.entity.User;
import com.sparta.springpracblog.entity.UserRoleEnum;
import com.sparta.springpracblog.jwt.JwtUtil;
import com.sparta.springpracblog.repository.BlogRepository;
import com.sparta.springpracblog.repository.CommentRepository;
import com.sparta.springpracblog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    private final JwtUtil jwtUtil;

    @Transactional
    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto, HttpServletRequest request) {
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
                    () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
            );

            Comment comment = new Comment(requestDto, user, blog);
            commentRepository.save(comment);

            return new CommentResponseDto(comment);
        } else {
            return null;
        }
    }

    @Transactional
    public CommentResponseDto update(Long blogId, Long commentId, CommentRequestDto requestDto, HttpServletRequest request) {
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

            Blog blog = blogRepository.findById(blogId).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 글입니다.")
            );

            Comment comment = commentRepository.findById(commentId).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
            );

            if(user.getRole() == UserRoleEnum.USER && !comment.getUser().getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("해당 사용자가 작성한 댓글이 아닙니다.");
            }

            comment.update(requestDto);
            return new CommentResponseDto(comment);
        } else {
            return null;
        }
    }

    @Transactional
    public ResponseEntity<String> deleteComment(Long blogId, Long commentId, HttpServletRequest request) {
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

            Blog blog = blogRepository.findById(blogId).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 글입니다.")
            );

            Comment comment = commentRepository.findById(commentId).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
            );


            if(user.getRole() == UserRoleEnum.USER && !comment.getUser().getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("해당 사용자가 작성한 댓글이 아닙니다.");
            }
            commentRepository.deleteById(commentId);
            return new ResponseEntity<>("성공", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
        }
    }
}
