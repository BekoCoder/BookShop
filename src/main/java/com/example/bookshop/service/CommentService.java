package com.example.bookshop.service;

import com.example.bookshop.dto.CommentsDto;
import com.example.bookshop.dto.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    ResponseDto<CommentsDto> addComment(CommentsDto commentsDto, Long id);

    ResponseDto<CommentsDto> updateComment(CommentsDto commentsDto, Long id);

    ResponseDto<String> deleteComment(Long id);

    ResponseDto<CommentsDto> getComment(Long id);

    Page<CommentsDto> getAllComments(Pageable pageable);
}
