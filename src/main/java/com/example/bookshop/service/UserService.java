package com.example.bookshop.service;

import com.example.bookshop.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    ResponseDto<UserDto> save(UserDto userDto);

    ResponseDto<JwtResponceDto> login(LoginRequestDto dto);

    ResponseDto<UserDto> update(UserDto userDto, Long id);

    Page<UserDto> getAllUser(Pageable pageable);

    ResponseDto<UserDto> getById(Long id);

    ResponseDto<String> deleteById(Long id);

    boolean isExistUser(String username);

    List<UserBasicDto> userWeek();

    ResponseDto<List<UserBookDto>> getUserBooks(Long userId);

    ResponseDto<List<UserDto>> getEveryMonthUsers(Integer month);

    ResponseDto<List<BasicDto>> mostActiveUsers();
}
