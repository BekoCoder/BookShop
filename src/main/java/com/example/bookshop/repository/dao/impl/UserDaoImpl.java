package com.example.bookshop.repository.dao.impl;

import com.example.bookshop.dto.BasicDto;
import com.example.bookshop.dto.UserBookDto;
import com.example.bookshop.dto.UserDto;
import com.example.bookshop.repository.dao.UserDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
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
                    and u.id = :userId
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

    @Override
    public List<UserDto> getEveryMonthUser(Integer month) {
        String sql = """
                SELECT FIRST_NAME, LAST_NAME, USERNAME
                FROM USERS
                WHERE EXTRACT(MONTH FROM CREATED_DATE) = :month
                """;
        List<Object[]> objects = entityManager.createNativeQuery(sql).setParameter("month", month).getResultList();
        List<UserDto> dto = new ArrayList<>();
        for (Object[] ob : objects) {
            UserDto userDto = new UserDto();
            userDto.setFirstName((String) ob[0]);
            userDto.setLastName((String) ob[1]);
            userDto.setUsername((String) ob[2]);
            dto.add(userDto);
        }
        return dto;
    }

    @Override
    public List<BasicDto> mostActiveUsers() {
        String sql = """
                select CAST(MAX(b.USERS_ID) as NUMBER) as user_id, CAST(COUNT((b.USERS_ID)) as number) as book_count, u.FIRST_NAME, u.LAST_NAME
                from BOUGHT_BOOKS b inner join USERS u on b.USERS_ID = u.ID group by b.USERS_ID, u.FIRST_NAME, u.LAST_NAME 
                order by COUNT((b.USERS_ID)) desc fetch first 1 row only
                """;
        List<Tuple> resultList = entityManager.createNativeQuery(sql, Tuple.class).getResultList();
        List<BasicDto> result = new ArrayList<>();
        for (Tuple row : resultList) {
            BasicDto dto = new BasicDto(
                    row.get("user_id", Number.class).longValue(),
                    row.get("book_count", Number.class).intValue(),
                    row.get("FIRST_NAME", String.class),
                    row.get("LAST_NAME", String.class)
            );
            result.add(dto);
        }
        return result;
    }
}
