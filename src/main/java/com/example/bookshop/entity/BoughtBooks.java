package com.example.bookshop.entity;

import com.example.bookshop.enumerations.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bought_books")
public class BoughtBooks extends AbstractEntity {
    private Double totalPrice;

    private Integer count;

    @JoinColumn(name = "users_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "books_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Books book;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


}
