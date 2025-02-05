package com.example.bookshop.exceptions;

public class UserNotFoundExceptions extends RuntimeException {
    public UserNotFoundExceptions(String message) {
        super(message);
    }
}
