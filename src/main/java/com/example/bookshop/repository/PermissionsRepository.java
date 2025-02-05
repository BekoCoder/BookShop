package com.example.bookshop.repository;

import com.example.bookshop.entity.Permissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long> {
    Optional<Permissions> findByName(String name);

    Page<Permissions> findAllByIsDeleted(Pageable pageable, int isDeleted);
}
