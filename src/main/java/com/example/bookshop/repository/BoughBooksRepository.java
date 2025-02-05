package com.example.bookshop.repository;

import com.example.bookshop.entity.BoughtBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoughBooksRepository extends JpaRepository<BoughtBooks, Long> {

}
