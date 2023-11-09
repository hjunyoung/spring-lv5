package com.example.springlv5.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // TODO
    //  staus: HttpCode - http status 말고 직접 정의한 코드로 대체하기
    //  message: default custom error message

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Unknown bad request"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Login failed"),
    INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "Incorrect password"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "Access denied"),

    // Resource
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found."),
    RESOURCE_CONFLICT(HttpStatus.CONFLICT, "Duplicated resource"),

    // JWT
    UNKNOWN_AUTH_ERROR(HttpStatus.UNAUTHORIZED, "Unknown authentication error"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid JWT token"),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "Unsupported JWT token"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Expired JWT token");


    private final HttpStatus status;
    private final String message;

    }
