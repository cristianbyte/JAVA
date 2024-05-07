package com.cristianbyte.beautyhub.api.dto.error_handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cristianbyte.beautyhub.api.dto.error.BaseResponse;
import com.cristianbyte.beautyhub.api.dto.error.ErrorsResponse;

@RestController
@ResponseStatus(code = HttpStatus.BAD_REQUEST)

public class BadRequestController {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleBadRequest(MethodArgumentNotValidException exception){
        
        List<String> errors = new ArrayList<>();

        exception.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        return ErrorsResponse.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .status(HttpStatus.BAD_REQUEST.name())
            .errors(errors)
            .build();
    }
}
