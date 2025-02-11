package com.example.bookshop.service.impl;

import com.example.bookshop.dto.CommentsDto;
import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.entity.Books;
import com.example.bookshop.entity.Comments;
import com.example.bookshop.entity.User;
import com.example.bookshop.exceptions.BooksException;
import com.example.bookshop.exceptions.CustomException;
import com.example.bookshop.repository.BooksRepository;
import com.example.bookshop.repository.CommentsRepository;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentsRepository commentsRepository;
    private final ModelMapper mapper;
    private final BooksRepository booksRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseDto<CommentsDto> addComment(CommentsDto commentsDto, Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new CustomException("Foydalanuvchi autentifikatsiya qilmagan !!!");
        }
        User user = (User) authentication.getPrincipal();
        Books books = booksRepository.findById(bookId).orElseThrow(() -> new BooksException("Bunday kitob topilmadi !!!"));
        ResponseDto<CommentsDto> responseDto = new ResponseDto<>();
        Comments comments = mapper.map(commentsDto, Comments.class);
        comments.setBook(books);
        comments.setUser(user);
        commentsRepository.save(comments);
        responseDto.setSuccess(true);
        responseDto.setMessage("Muvafaqqiyatli qo'shildi !!!");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(comments, CommentsDto.class));
        return responseDto;

    }

    @Override
    public ResponseDto<CommentsDto> updateComment(CommentsDto commentsDto, Long id) {
        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new CustomException("Comment topilmadi"));
        ResponseDto<CommentsDto> responseDto = new ResponseDto<>();
        comments.setComments(commentsDto.getComments());
        responseDto.setSuccess(true);
        responseDto.setMessage("Muvafaqqiyatli qo'shildi !!!");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(comments, CommentsDto.class));
        return responseDto;
    }

    @Override
    public ResponseDto<String> deleteComment(Long id) {
        ResponseDto<String> responseDto = new ResponseDto<>();
        commentsRepository.deleteById(id);
        responseDto.setSuccess(true);
        responseDto.setMessage("Muvafaqqiyatli o'chirildi !!!");
        return responseDto;
    }

    @Override
    public ResponseDto<CommentsDto> getComment(Long id) {
        ResponseDto<CommentsDto> responseDto = new ResponseDto<>();
        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new CustomException("Comment topilmadi"));
        responseDto.setSuccess(true);
        responseDto.setMessage("Muvafaqqiyatli qaytarildi !!!");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(comments, CommentsDto.class));
        return responseDto;
    }

    @Override
    public Page<CommentsDto> getAllComments(Pageable pageable) {
        return commentsRepository.findAllByIsDeleted(pageable, 0).map(commentsDto -> mapper.map(commentsDto, CommentsDto.class));
    }

}
