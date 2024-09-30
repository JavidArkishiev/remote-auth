package com.example.remoteauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandle {


    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<ErrorDetails> handleExistEmailException(EmailExistException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotfoundException(UserNotFoundException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }

}
