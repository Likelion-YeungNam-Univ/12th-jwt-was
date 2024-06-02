package com.example.demo.global.exception;

import com.example.demo.global.response.ApiExceptionResponse;
import com.example.demo.global.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ApiResponse<ApiExceptionResponse> handleException(Exception ex) {
        log.error("예외 발생: " + ex.toString());

        final ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                500,
                ex.toString()
        );
        return ApiResponse.createError(apiExceptionResponse);
    }
    

}
