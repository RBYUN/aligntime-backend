package io.aligntime.users.controller;

import io.aligntime.users.exception.AlignTimeErrorResponse;
import io.aligntime.users.exception.UserAlreadyExistsException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingController {


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<AlignTimeErrorResponse> handleUserExists(UserAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new AlignTimeErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        e.getMessage()));
    }

    @ExceptionHandler(CannotGetJdbcConnectionException.class)
    public ResponseEntity<AlignTimeErrorResponse> handleDBConnectionException(CannotGetJdbcConnectionException e) {
        return ResponseEntity.
                status(HttpStatus.GATEWAY_TIMEOUT).
                body(new AlignTimeErrorResponse(
                        HttpStatus.GATEWAY_TIMEOUT.value(),
                        "There was a database connectivity issue"));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<AlignTimeErrorResponse> handleDBException(DataAccessException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new AlignTimeErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "There was an error hitting the database"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AlignTimeErrorResponse> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new AlignTimeErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "An unexpected error occurred"));
    }

}
