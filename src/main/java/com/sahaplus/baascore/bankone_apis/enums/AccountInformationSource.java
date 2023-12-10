package com.sahaplus.baascore.bankone_apis.enums;

import lombok.Getter;

@Getter
public enum AccountInformationSource implements PersistableEnum<String> {
    Others("0"),
    FriendOrFamily("1"),
    BankBranch("2"),
    RoadShow("3"),
    MarketStorm("4"),
    TownHall("5");

    private final String value;

    AccountInformationSource(String value) {
        this.value = value;
    }

    public static class Converter extends EnumValueTypeConverter<AccountInformationSource, String> {
        public Converter() {
            super(AccountInformationSource.class);
        }
    }
}
