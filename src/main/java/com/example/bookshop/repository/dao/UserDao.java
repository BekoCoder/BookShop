package com.example.bookshop.repository.dao;

import com.example.bookshop.dto.BasicDto;
import com.example.bookshop.dto.UserBookDto;
import com.example.bookshop.dto.UserDto;

import java.util.List;

public interface UserDao {
    String getUserRoleName(Long userId);

    List<UserBookDto> getUserBooks(Long userId);

    List<UserDto> getEveryMonthUser(Integer month);

    List<BasicDto> mostActiveUsers();
}
