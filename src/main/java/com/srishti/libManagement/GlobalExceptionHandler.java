package com.srishti.libManagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String>
    handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>
    handleExceptions(Exception e) {
        return new ResponseEntity<>("An unknown error occurred." + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
