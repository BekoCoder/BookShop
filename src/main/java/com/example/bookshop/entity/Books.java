package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Books extends AbstractEntity {
    private String title;
    private String author;
    private String description;
    private Double price;
    private Integer quantity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private List<Comments> comments;

    @OneToMany(mappedBy = "book")
    private List<BoughtBooks> boughtBooks;

    @JoinColumn(name = "author_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Authors authors;

}
