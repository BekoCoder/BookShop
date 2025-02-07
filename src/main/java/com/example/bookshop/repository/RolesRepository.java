package com.example.bookshop.repository;

import com.example.bookshop.entity.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String name);

    Page<Roles> findAllByIsDeleted(Pageable pageable, Integer isDeleted);
}
