package com.example.bookshop.service;

import com.example.bookshop.dto.BoughtBooksDto;
import com.example.bookshop.dto.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoughtBookService {
    ResponseDto<String> save(Long bookId, Integer quantity);

    ResponseDto<BoughtBooksDto> update(BoughtBooksDto boughtBooksDto, Long id);

    ResponseDto<String> delete(Long id);

    ResponseDto<BoughtBooksDto> get(Long id);

    Page<BoughtBooksDto> getAll(Pageable pageable);

    ResponseDto<String> checkOut(Long boughtBookId);

    ResponseDto<String> cancel(Long boughtBookId);
}
