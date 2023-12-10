package com.sahaplus.baascore.bankone_apis.enums;

import lombok.Getter;

@Getter
public enum Gender implements PersistableEnum<String> {
    MALE("0"),
    FEMALE("1");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public static class Converter extends EnumValueTypeConverter<Gender, String> {
        public Converter() {
            super(Gender.class);
        }
    }
}
