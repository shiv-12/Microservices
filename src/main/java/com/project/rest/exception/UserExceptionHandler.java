package com.project.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// This is for handling all the kinds of user exceptions
@ControllerAdvice
public class UserExceptionHandler {

    //    This will automatically call when UserNotFoundException exception throws
    @ExceptionHandler
    public ResponseEntity<UserResponse>
    handleUserException(UserNotFoundException exception) {

        UserResponse userResponse = new UserResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage()
        );
        return new ResponseEntity<>(userResponse, HttpStatus.NOT_FOUND);

    }
}
