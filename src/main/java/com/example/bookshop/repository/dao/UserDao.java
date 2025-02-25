package com.example.bookshop.repository.dao;

import com.example.bookshop.dto.UserBookDto;

import java.util.List;

public interface UserDao {
    String getUserRoleName(Long userId);

    List<UserBookDto> getUserBooks(Long userId);
}
