package com.cristianbyte.hire_hub_jpa_dto.controller.errors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cristianbyte.hire_hub_jpa_dto.utils.dto.error.BaseErrorResponse;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.error.ErrorResponse;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.error.ErrorsResponse;
import com.cristianbyte.hire_hub_jpa_dto.utils.exeptions.IdNotFound;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestController {
    //when WILL do BOOM
    @ExceptionHandler(IdNotFound.class)
    public BaseErrorResponse handleNotFound(IdNotFound e){
        return ErrorResponse.builder()
        .message(e.getMessage())
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
