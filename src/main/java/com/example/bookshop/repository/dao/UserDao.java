package com.example.bookshop.repository.dao;

import com.example.bookshop.dto.*;

import java.util.List;

public interface UserDao {
    String getUserRoleName(Long userId);

    List<UserBookDto> getUserBooks(Long userId);

    List<UserDto> getEveryMonthUser(Integer month);

    List<BasicDto> mostActiveUsers();

    List<UserBasicDto> getLastWeek();

    List<UserBuyDto> getUserBuys();

    List<CommentsDto> getComments();
}
