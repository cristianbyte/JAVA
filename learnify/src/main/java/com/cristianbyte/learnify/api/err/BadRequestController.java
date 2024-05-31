package com.cristianbyte.learnify.api.err;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cristianbyte.learnify.util.exception.BaseErrorResponse;
import com.cristianbyte.learnify.util.exception.ErrorsResponse;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestController {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseErrorResponse handleJsonParseException(HttpMessageNotReadableException ex) {
        return ErrorsResponse.builder()
                .message("Check your Json format. " + ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public BaseErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
        return ErrorsResponse.builder()
                .message("Invalid value for enum: " + ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handlerErrors(MethodArgumentNotValidException e){
        List<String> errors = new ArrayList<>();
        e.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        return ErrorsResponse.builder()
        .errors(errors)
        .status(HttpStatus.BAD_REQUEST.name())
        .code(HttpStatus.BAD_REQUEST.value())
        .build();
    }
}
