package com.spring.rest.api.mybooks.exception.handler;


import com.spring.rest.api.mybooks.entity.ErrorType;
import com.spring.rest.api.mybooks.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.time.LocalDateTime.now;

// https://java-master.com/controlleradvice-обработка-ошибок-в-spring/
@RestControllerAdvice
public class BookErrorHandler {


    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorType> handleNotFound(BookNotFoundException bookNotFoundException) {
        return new ResponseEntity<ErrorType>(ErrorType.builder().
                status(HttpStatus.NOT_FOUND)
                .message(bookNotFoundException.getMessage())
                .time(now())
                .build(), HttpStatus.NOT_FOUND);
    }
}
