package com.example.bookshop.repository;

import com.example.bookshop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u where u.username=:username")
    Optional<User> findByUsername(String username);


    Page<User> findAllByIsDeleted(Pageable pageable, int isDeleted);
}
