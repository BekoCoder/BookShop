package com.example.bookshop.service;

import com.example.bookshop.dto.BooksDto;
import com.example.bookshop.dto.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BooksService {
    ResponseDto<BooksDto> addBook(BooksDto booksDto);

    ResponseDto<BooksDto> updateBook(BooksDto booksDto, Long id);

    ResponseDto<String> deleteBook(Long id);

    ResponseDto<BooksDto> getBookById(Long id);

    Page<BooksDto> getAllBooks(Pageable pageable);
}
