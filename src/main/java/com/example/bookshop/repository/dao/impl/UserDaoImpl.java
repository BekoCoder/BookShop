package com.example.bookshop.repository.dao.impl;

import com.example.bookshop.dto.*;
import com.example.bookshop.repository.dao.UserDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
                select
                    u.ID,
                    u.FIRST_NAME,
                    u.LAST_NAME, 
                    u.USERNAME, 
                    p.TITLE, 
                    p.PRICE, 
                    b.TOTAL_PRICE
                from USERS u
                         inner join BOUGHT_BOOKS b on u.ID = b.USERS_ID
                         inner join BOOKS p on b.BOOKS_ID = p.ID
                    and u.id = :userId
                """;
        Query query = entityManager.createNativeQuery(sql, UserBookDto.class)
                .setParameter("userId", userId);

        return query.getResultList();

    }

    @Override
    public List<UserDto> getEveryMonthUser(Integer month) {
        String sql = """
                SELECT
                    t.ID,
                    t.FIRST_NAME,
                    t.LAST_NAME,
                    t.USERNAME,
                    NULL AS PASSWORD
                FROM USERS t
                WHERE EXTRACT(MONTH FROM CREATED_DATE) = :month
                """;
        Query query = entityManager.createNativeQuery(sql, UserDto.class);
        query.setParameter("month", month);

        return query.getResultList();
    }

    @Override
    public List<BasicDto> mostActiveUsers() {
        String sql = """
                select u.ID,
                 CAST(COUNT((b.USERS_ID)) as number) as book_count,
                 u.FIRST_NAME,
                 u.LAST_NAME
                 from BOUGHT_BOOKS b
                 inner join USERS u on b.USERS_ID = u.ID
                 where b.ORDER_STATUS = 'COMPLETED'
                group by b.USERS_ID, u.FIRST_NAME, u.LAST_NAME, u.ID
                order by COUNT((b.USERS_ID)) desc fetch first (SELECT COUNT(*) FROM USERS) row only
                """;
        Query query = entityManager.createNativeQuery(sql, BasicDto.class);
        return query.getResultList();
    }

    @Override
    public List<UserBasicDto> getLastWeek() {
        String sql = """
                SELECT u.ID,
                       u.FIRST_NAME, 
                       u.LAST_NAME, 
                       u.USERNAME, 
                       u.CREATED_DATE 
                FROM USERS u 
                WHERE CREATED_DATE >= SYSDATE - 7
                """;

        Query query = entityManager.createNativeQuery(sql, UserBasicDto.class);
        return query.getResultList();
    }

    @Override
    public List<UserBuyDto> getUserBuys() {
        String sql = """
                SELECT u.ID,
                       u.FIRST_NAME,
                       u.LAST_NAME,
                       SUM(b.TOTAL_PRICE) AS total_price
                FROM USERS u
                JOIN BOUGHT_BOOKS b ON u.ID = b.USERS_ID 
                where b.ORDER_STATUS='COMPLETED'
                group by u.ID, u.FIRST_NAME, u.LAST_NAME
                order by total_price desc fetch first 
                (SELECT COUNT(*) FROM USERS) row only
                """;
        Query query = entityManager.createNativeQuery(sql, UserBuyDto.class);
        return query.getResultList();
    }

    @Override
    public List<CommentsDto> getComments() {
        String sql= """
                SELECT u.ID,
                       u.FIRST_NAME,
                       u.LAST_NAME,
                       c.COMMENTS,
                       b.TITLE
                FROM USERS u inner join COMMENTS c on u.ID = c.USERS_ID
                             inner join BOOKS b on c.BOOKS_ID=b.ID
                """;
        Query query = entityManager.createNativeQuery(sql, CommentsDto.class);
        return  query.getResultList();
    }
}
