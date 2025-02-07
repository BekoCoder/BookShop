package com.example.bookshop.repository;

import com.example.bookshop.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    Page<Comments> findAllByIsDeleted(Pageable pageable, Integer isDeleted);
}
