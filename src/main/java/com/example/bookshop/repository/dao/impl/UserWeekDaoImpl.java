package com.example.bookshop.repository.dao.impl;

import com.example.bookshop.dto.UserBasicDto;
import com.example.bookshop.repository.dao.UserWeekDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserWeekDaoImpl implements UserWeekDao {
    private final EntityManager manager;


    @Override
    public List<UserBasicDto> getLastWeek() {

        String sql = """
                SELECT FIRST_NAME, LAST_NAME, USERNAME, CREATED_DATE 
                FROM USERS 
                WHERE CREATED_DATE >= SYSDATE - 7
                """;

        try {
            Query nativeQuery = manager.createNativeQuery(sql);
            List<Object[]> resultList = nativeQuery.getResultList();

            List<UserBasicDto> result = new ArrayList<>();
            for (Object[] row : resultList) {
                UserBasicDto dto = new UserBasicDto();
                dto.setFirstName((String) row[0]);
                dto.setLastName((String) row[1]);
                dto.setUserName((String) row[2]);
                dto.setCreatedDate(((java.sql.Timestamp) row[3]).toLocalDateTime());
                result.add(dto);
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
