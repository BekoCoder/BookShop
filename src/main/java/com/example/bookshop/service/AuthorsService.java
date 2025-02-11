package com.example.bookshop.service;

import com.example.bookshop.dto.AuthorsDto;
import com.example.bookshop.dto.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorsService {
    ResponseDto<AuthorsDto> addAuthor(AuthorsDto authorsDto);

    ResponseDto<AuthorsDto> updateAuthor(AuthorsDto authorsDto, Long id);

    ResponseDto<String> deleteAuthor(Long id);

    ResponseDto<AuthorsDto> getAuthor(Long id);

    Page<AuthorsDto> getAuthors(Pageable pageable);

    ResponseDto<String> assignBookToAuthor(Long bookId, Long authorId);

}
