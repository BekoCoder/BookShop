package com.example.bookshop.repository.dao.impl;

import com.example.bookshop.dto.UserBookDto;
import com.example.bookshop.repository.dao.UserDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final EntityManager entityManager;

    @Override
    public String getUserRoleName(Long userId) {
        String sql = """
                
                SELECT u.username
                FROM Users u
                INNER JOIN user_role ur ON u.id = ur.user_id
                INNER JOIN Roles r ON ur.role_id = r.id
                WHERE r.name = 'AUTHOR' AND u.id = :userId
                
                """;
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter("userId", userId);
        try {
            return (String) nativeQuery.getSingleResult();
        } catch (Exception e) {
            return null;

        }
    }

    @Override
    public List<UserBookDto> getUserBooks(Long userId) {
        String sql = """
                select u.FIRST_NAME, u.LAST_NAME, u.USERNAME, p.TITLE, p.PRICE, b.TOTAL_PRICE
                from USERS u
                         inner join BOUGHT_BOOKS b on u.ID = b.USERS_ID
                         inner join BOOKS p on b.BOOKS_ID = p.ID
                    and u.id = :userId;
                """;
        List<Object[]> resultList = entityManager.createNativeQuery(sql)
                .setParameter("userId", userId)
                .getResultList();

        List<UserBookDto> result = new ArrayList<>();
        for (Object[] row : resultList) {
            UserBookDto dto = new UserBookDto();
            dto.setFirstName((String) row[0]);
            dto.setLastName((String) row[1]);
            dto.setUsername((String) row[2]);
            dto.setTitle((String) row[3]);
            dto.setPrice((Double) row[4]);
            dto.setTotalPrice((Double) row[5]);
            result.add(dto);
        }
        return result;
    }
}
