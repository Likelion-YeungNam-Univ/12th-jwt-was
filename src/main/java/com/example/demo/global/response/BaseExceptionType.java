package com.example.demo.global.response;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {

    int errorCode();
    HttpStatus httpStatus();
    String errorMessage();
    void setErrorMessage(String message);
}
