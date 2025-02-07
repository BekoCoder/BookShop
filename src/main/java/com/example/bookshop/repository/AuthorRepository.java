package com.example.bookshop.repository;

import com.example.bookshop.entity.Authors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Authors, Long> {
    Optional<Authors> findByName(String name);

    Page<Authors> findAllByIsDeleted(Pageable pageable, Integer isDeleted);
}
