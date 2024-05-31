package com.cristianbyte.learnify.util.exception;


import org.springframework.http.HttpStatus;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerController {
    @ExceptionHandler(CannotCreateTransactionException.class)
    public BaseErrorResponse handleCannotCreateTransactionException(CannotCreateTransactionException e) {
        return ErrorsResponse.builder()
                .message("A transaction could not be opened for the database. Please try again later.")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public BaseErrorResponse handleGlobalException(Exception e) {
        return ErrorsResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.name())
                .code(HttpStatus.NOT_FOUND.value())
                .build();
    }
}
