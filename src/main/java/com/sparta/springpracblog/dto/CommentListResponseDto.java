package com.sparta.springpracblog.dto;

import com.sparta.springpracblog.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class CommentListResponseDto {

    private List<CommentResponseDto> commentList = new ArrayList<>();

    public void addComment(CommentResponseDto responseDto) {
        commentList.add(responseDto);
    }
}
