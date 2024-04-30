package com.anderson.app.geoapp.error;

import com.anderson.app.geoapp.exceptions.FeatureNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(FeatureNotFoundException.class)
    public ResponseEntity<ApiError> handleFeatureNotFoundException(FeatureNotFoundException e, HttpServletRequest httpServletRequest) {
        ApiError apiError = ApiError.builder()
                .path(httpServletRequest.getRequestURI())
                .method(httpServletRequest.getMethod())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

}
