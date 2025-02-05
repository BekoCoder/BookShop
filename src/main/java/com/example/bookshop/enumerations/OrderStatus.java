package com.example.bookshop.enumerations;

public enum OrderStatus {
    NEW("Yangi"),
    IN_PROGRESS("Jarayonda"),
    COMPLETED("Tugallangan"),
    CANCELED("Bekor qilingan");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }
}
