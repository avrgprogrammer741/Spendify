package com.Spendify.Spendify.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleException(ResourceNotFoundException e,
                                                    HttpServletRequest request) {
        List<String> additionalDataList = new ArrayList<>();

        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                LocalDateTime.now(),
                additionalDataList
        );

    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleException(DuplicateResourceException e,
                                                    HttpServletRequest request) {
        List<String> additionalDataList = new ArrayList<>();

        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                LocalDateTime.now(),
                additionalDataList
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiError> manageValidationException(HttpServletResponse response,
                                                              HttpServletRequest request,
                                                              MethodArgumentNotValidException ex) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        List<String> additionalDataList = new ArrayList<>();
        for (FieldError fieldError : ex.getFieldErrors()) {
            additionalDataList.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        log.error("MethodArgumentNotValidException", ex);

        ApiError apiError = ApiError.builder()
                .path(request.getRequestURI())
                .message("Method arguments not valid!")
                .localDateTime(LocalDateTime.now())
                .additionalData(additionalDataList)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}