package com.example.bookshop.repository.dao;

import com.example.bookshop.dto.UserBasicDto;

import java.util.List;

public interface UserWeekDao {

    List<UserBasicDto> getLastWeek();
}
