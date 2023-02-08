package com.sparta.springpracblog.service;


import com.sparta.springpracblog.dto.BlogRequestDto;
import com.sparta.springpracblog.entity.Blog;
import com.sparta.springpracblog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    @Transactional
    public Blog createBlog(BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto);
        blogRepository.save(blog);
        return blog;
    }

    @Transactional(readOnly = true)
    public List<Blog> getBlogs() {
        return blogRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public Optional<Blog> getBlog(Long id) {
        return blogRepository.findById(id);
    }

    @Transactional
    public Long update(Long id, BlogRequestDto requestDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        if (blog.getPassword().equals(requestDto.getPassword())) {
            blog.update(requestDto);
            return blog.getId();
        } else {
            return -1L;
        }
    }

    @Transactional
    public Long deleteBlog(Long id, String password) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        if (blog.getPassword().equals(password)) {
            blogRepository.deleteById(id);
            return id;
        } else {
            return -1L;
        }
    }

}
