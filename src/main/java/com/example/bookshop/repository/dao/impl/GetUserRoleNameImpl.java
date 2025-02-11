package com.example.bookshop.repository.dao.impl;

import com.example.bookshop.repository.dao.GetUserRoleName;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GetUserRoleNameImpl implements GetUserRoleName {
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
}
