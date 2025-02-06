package com.example.bookshop.exceptions;

import com.example.bookshop.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundExceptions.class)
    public ResponseEntity<Object> handleCustomException(UserNotFoundExceptions ex) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setSuccess(false);
        responseDto.setReason(ex.getMessage());
        return ResponseEntity.badRequest().body(responseDto);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setSuccess(false);
        responseDto.setReason(e.getMessage());
        return ResponseEntity.badRequest().body(responseDto);
    }


    @ExceptionHandler(BooksException.class)
    public ResponseEntity<Object> handleCustomException(BooksException e) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setSuccess(false);
        responseDto.setReason(e.getMessage());
        return ResponseEntity.badRequest().body(responseDto);
    }
}
