package com.sahaplus.baascore.enums;

import lombok.Getter;

@Getter
public enum BankOneResponseCode {
    SUCCESS("200"),
    INVALID_REQUEST("400"),
    UNAUTHORIZED("401"),
    NOT_FOUND("404"),
    SERVER_ERROR("500");

    private final String code;

    BankOneResponseCode(String code) {
        this.code = code;
    }

}
