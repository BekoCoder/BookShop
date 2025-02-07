package com.example.bookshop.exceptions;

public class PermissionException extends RuntimeException{
    public PermissionException(String message) {
        super(message);
    }
}
