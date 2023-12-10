package com.sahaplus.baascore.bankone_apis.exception;

import com.sahaplus.baascore.bankone_apis.util.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AppException.class)
    public final ResponseEntity<Object> handleApiException(AppException e) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(e.getErrorCode());
        response.setResponseMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
    }
}
