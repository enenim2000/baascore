package com.sahaplus.baascore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handlerGeneralException(final ApiException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setHttpStatus(badRequest);
        return new ResponseEntity<>(errorResponse, badRequest);
    }
}
