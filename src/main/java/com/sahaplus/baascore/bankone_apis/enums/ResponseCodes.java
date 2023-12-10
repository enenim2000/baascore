package com.sahaplus.baascore.bankone_apis.enums;

import lombok.Getter;

@Getter
public enum ResponseCodes {
    SUCCESSFUL("00"),
    VALIDATION_ERROR("01"),
    SERVER_ERROR("99"),

    /* Backbone */
    B_SUCCESSFUL("B00"),
    B_VALIDATION_ERROR("B01"),
    B_SERVER_ERROR("B99");

    private final String value;

    ResponseCodes(String value) {
        this.value = value;
    }
}
