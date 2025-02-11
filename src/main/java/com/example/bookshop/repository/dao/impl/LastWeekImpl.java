package com.example.bookshop.repository.dao.impl;

import com.example.bookshop.dto.UserBasicDto;
import com.example.bookshop.entity.User;
import com.example.bookshop.repository.dao.LastWeek;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class LastWeekImpl implements LastWeek {
    private final EntityManager manager;


    @Override
    public List<UserBasicDto> getLastWeek() {
        List<UserBasicDto> list = new ArrayList<>();
        String sql= """
                select * from USERS where CREATED_DATE>=SYSDATE-7
                """;
        try {
            Query nativeQuery = manager.createNativeQuery(sql, UserBasicDto.class);
            list = nativeQuery.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;


    }
}
