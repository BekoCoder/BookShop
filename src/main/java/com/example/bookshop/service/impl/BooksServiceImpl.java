package com.example.bookshop.service.impl;

import com.example.bookshop.dto.BooksDto;
import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.entity.Books;
import com.example.bookshop.exceptions.BooksException;
import com.example.bookshop.repository.BooksRepository;
import com.example.bookshop.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {
    private final BooksRepository booksRepository;
    private final ModelMapper mapper;

    @Override
    public ResponseDto<BooksDto> addBook(BooksDto booksDto) {
        ResponseDto<BooksDto> responseDto = new ResponseDto<>();
        Books books = mapper.map(booksDto, Books.class);
        if (isBookExist(books.getTitle())) {
            throw new BooksException("Kitob allaqachon qo'shilgan !!!");
        }
        booksRepository.save(books);
        responseDto.setSuccess(true);
        responseDto.setMessage("Kitob muvafaqqiyatli qo'shildi");
        responseDto.setRecordsTotal(booksRepository.count());
        responseDto.setData(mapper.map(books, BooksDto.class));
        return responseDto;

    }

    @Override
    public ResponseDto<BooksDto> updateBook(BooksDto booksDto, Long id) {
        ResponseDto<BooksDto> responseDto = new ResponseDto<>();
        Books books = booksRepository.findById(id).orElseThrow(() -> new BooksException("Kitob topilmadi !!!"));
        if (isBookExist(books.getTitle())) {
            throw new BooksException("Bunday nomli kitob qo'shilgan !!!");
        }
        booksRepository.save(books);
        books.setTitle(booksDto.getTitle());
        books.setQuantity(booksDto.getQuantity());
        responseDto.setSuccess(true);
        responseDto.setMessage("Kitob muvafaqqiyatli yangilandi");
        responseDto.setRecordsTotal(booksRepository.count());
        responseDto.setData(mapper.map(books, BooksDto.class));
        return responseDto;
    }

    @Override
    public ResponseDto<String> deleteBook(Long id) {
        ResponseDto<String> responseDto = new ResponseDto<>();
        Books books = booksRepository.findById(id).orElseThrow(() -> new BooksException("Kitob topilmadi !!!"));
        if (books.getIsDeleted() == 1) {
            throw new BooksException("Kitob allaqachon o'chirilgan");
        }
        books.setIsDeleted(1);
        responseDto.setSuccess(true);
        responseDto.setMessage("Kitob muvafaqqiyatli o'chirildi !!!");
        responseDto.setRecordsTotal(booksRepository.count());
        return responseDto;
    }

    @Override
    public ResponseDto<BooksDto> getBookById(Long id) {
        Books books = booksRepository.findById(id).orElseThrow(() -> new BooksException("Kitob topilmadi !!!"));
        ResponseDto<BooksDto> responseDto = new ResponseDto<>();
        if (books.getIsDeleted() == 1) {
            throw new BooksException("Kitob o'chirilgan");
        }
        responseDto.setSuccess(true);
        responseDto.setRecordsTotal(booksRepository.count());
        responseDto.setMessage("Id orqali kitob qaytarildi");
        responseDto.setData(mapper.map(books, BooksDto.class));
        return responseDto;
    }

    @Override
    public Page<BooksDto> getAllBooks(Pageable pageable) {
        return booksRepository.findAllByIsDeleted(pageable, 0).map(books -> mapper.map(books, BooksDto.class));
    }

    private boolean isBookExist(String title) {
        return booksRepository.findByTitle(title).isPresent();
    }
}
