package com.example.bookshop.service.impl;

import com.example.bookshop.dto.BoughtBooksDto;
import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.entity.Books;
import com.example.bookshop.entity.BoughtBooks;
import com.example.bookshop.entity.User;
import com.example.bookshop.enumerations.OrderStatus;
import com.example.bookshop.exceptions.BooksException;
import com.example.bookshop.exceptions.CustomException;
import com.example.bookshop.repository.BooksRepository;
import com.example.bookshop.repository.BoughBooksRepository;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.BoughtBookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoughtBookServiceImpl implements BoughtBookService {
    private final BoughBooksRepository boughBooksRepository;
    private final ModelMapper mapper;
    private final BooksRepository booksRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<String> save(Long bookId, Integer quantity) {
        ResponseDto<String> responseDto = new ResponseDto<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Books books = booksRepository.findById(bookId).orElseThrow(() -> new BooksException("Kitob topilmadi"));
        if(books.getIsDeleted()==1){
            throw new BooksException("Kitob o'chirilgan");
        }
        if(Objects.isNull(user) || user.getIsDeleted()==1){
            throw new CustomException("Foydalanuvchi o'chirilgan !!!");
        }
        if(books.getQuantity()<quantity){
            throw new CustomException("Kiritilgan miqdor kitob miqdoridan katta !!!");
        }
        BoughtBooks boughtBooks=new BoughtBooks();
        boughtBooks.setUser(user);
        boughtBooks.setBook(books);
        boughtBooks.setTotalPrice(books.getPrice()*quantity);
        boughtBooks.setOrderStatus(OrderStatus.NEW);
        boughBooksRepository.save(boughtBooks);
        responseDto.setSuccess(true);
        responseDto.setMessage("Kitob muvaffaqiyatli savatga qo'shildi");
        responseDto.setRecordsTotal(boughBooksRepository.count());
        return responseDto;
    }

    @Override
    public ResponseDto<BoughtBooksDto> update(BoughtBooksDto boughtBooksDto, Long id) {
        return null;
    }

    @Override
    public ResponseDto<String> delete(Long id) {
        return null;
    }

    @Override
    public ResponseDto<BoughtBooksDto> get(Long id) {
        return null;
    }

    @Override
    public Page<BoughtBooksDto> getAll(Pageable pageable) {
        return null;
    }
}
