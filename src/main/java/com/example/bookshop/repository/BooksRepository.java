package com.example.bookshop.repository;

import com.example.bookshop.entity.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
    @Query("select b from Books b where b.title=:title")
    Optional<Books> findByTitle(String title);

    Page<Books> findAllByIsDeleted(Pageable pageable, Integer isDeleted);
}
