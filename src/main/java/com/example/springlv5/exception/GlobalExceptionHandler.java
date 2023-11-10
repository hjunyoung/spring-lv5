package com.example.springlv5.exception;

import com.example.springlv5.global.ApiResponse.FailBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j(topic = "Global Exception Handler")
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = DuplicatedException.class)
    public ResponseEntity<FailBody> DuplicatedExceptionHandler(
        DuplicatedException e) {
        log.info("DuplicatedException logging");
        FailBody response = FailBody.builder()
            .message(e.getLocalizedMessage())
            .build();
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(response);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<FailBody> NotFoundExceptionHandler(
        DuplicatedException e) {
        log.info("NotFoundException logging");
        String message = e.getLocalizedMessage();

        FailBody response = FailBody.builder()
            .message(e.getLocalizedMessage())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(response);
    }
}
