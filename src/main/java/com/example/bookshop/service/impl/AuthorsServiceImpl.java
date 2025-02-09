package com.example.bookshop.service.impl;

import com.example.bookshop.dto.AuthorsDto;
import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.entity.Authors;
import com.example.bookshop.exceptions.AuthorException;
import com.example.bookshop.repository.AuthorRepository;
import com.example.bookshop.service.AuthorsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorsServiceImpl implements AuthorsService {
    private final AuthorRepository authorRepository;
    private final ModelMapper mapper;

    @Override
    public ResponseDto<AuthorsDto> addAuthor(AuthorsDto authorsDto) {
        ResponseDto<AuthorsDto> responseDto = new ResponseDto<>();
        Authors authors = mapper.map(authorsDto, Authors.class);
        if (isExistAuthor(authors.getName())) {
            throw new AuthorException("Avtor allaqachon mavjud !!!");
        }
        authorRepository.save(authors);
        responseDto.setSuccess(true);
        responseDto.setMessage("Muallif qo'shildi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(authors, AuthorsDto.class));
        return responseDto;

    }

    @Override
    public ResponseDto<AuthorsDto> updateAuthor(AuthorsDto authorsDto, Long id) {
        ResponseDto<AuthorsDto> responseDto = new ResponseDto<>();
        Authors authors = authorRepository.findById(id).orElseThrow(() -> new AuthorException("Avtor topilmadi !!!"));
        if (authors.getIsDeleted() == 1) {
            throw new AuthorException("Avtor o'chirilgan !!!");
        }
        authors.setName(authorsDto.getName());
        authorRepository.save(authors);
        responseDto.setSuccess(true);
        responseDto.setMessage("Muallif yangilandi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(authors, AuthorsDto.class));
        return responseDto;
    }

    @Override
    public ResponseDto<String> deleteAuthor(Long id) {
        ResponseDto<String> responseDto = new ResponseDto<>();
        Authors authors = authorRepository.findById(id).orElseThrow(() -> new AuthorException("Avtor topilmadi !!!"));
        if (authors.getIsDeleted() == 1) {
            throw new AuthorException("Avtor o'chirilgan !!!");
        }
        authors.setIsDeleted(1);
        authorRepository.save(authors);
        responseDto.setSuccess(true);
        responseDto.setMessage("Muallif o'chirildi");
        responseDto.setRecordsTotal(1L);
        return responseDto;
    }

    @Override
    public ResponseDto<AuthorsDto> getAuthor(Long id) {
        ResponseDto<AuthorsDto> responseDto = new ResponseDto<>();
        Authors authors = authorRepository.findById(id).orElseThrow(() -> new AuthorException("Avtor topilmadi !!!"));
        if (authors.getIsDeleted() == 1) {
            throw new AuthorException("Avtor o'chirilgan !!!");
        }
        responseDto.setSuccess(true);
        responseDto.setMessage("Muallif nomi: " + authors.getName());
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(authors, AuthorsDto.class));
        return responseDto;

    }

    @Override
    public Page<AuthorsDto> getAuthors(Pageable pageable) {
        return authorRepository.findAllByIsDeleted(pageable, 0).map(author -> mapper.map(author, AuthorsDto.class));
    }

    private boolean isExistAuthor(String name) {
        return authorRepository.findByName(name).isPresent();
    }
}
