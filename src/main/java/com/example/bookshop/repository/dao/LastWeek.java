package com.example.bookshop.repository.dao;

import com.example.bookshop.dto.UserBasicDto;
import jakarta.persistence.Tuple;

import java.util.List;

public interface LastWeek {

    List<UserBasicDto> getLastWeek();
}
