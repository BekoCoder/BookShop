package com.example.bookshop.service;

import com.example.bookshop.dto.JwtResponceDto;
import com.example.bookshop.dto.LoginRequestDto;
import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    ResponseDto<UserDto> save(UserDto userDto);

    ResponseDto<JwtResponceDto> login(LoginRequestDto dto);

    ResponseDto<UserDto> update(UserDto userDto, Long id);

    Page<UserDto> getAllUser(Pageable pageable);

    ResponseDto<UserDto> getById(Long id);

    ResponseDto<String> deleteById(Long id);

    boolean isExistUser(String username);
}
