package com.sahaplus.baascore.enums;

import com.sahaplus.baascore.bankone_apis.enums.EnumValueTypeConverter;
import com.sahaplus.baascore.bankone_apis.enums.PersistableEnum;
import lombok.Getter;

@Getter
public enum AccountTier implements PersistableEnum<String> {
    TIER1("1"),
    TIER2("2"),
    TIER3("3");

    private final String value;

    AccountTier(String value) {
        this.value = value;
    }

    public static class Converter extends EnumValueTypeConverter<AccountTier, String> {
        public Converter() {
            super(AccountTier.class);
        }
    }
}
