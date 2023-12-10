package com.sahaplus.baascore.bankone_apis.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {

    private String errorCode;

    public AppException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
