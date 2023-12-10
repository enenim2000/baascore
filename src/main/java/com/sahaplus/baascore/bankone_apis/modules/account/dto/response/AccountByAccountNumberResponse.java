package com.sahaplus.baascore.bankone_apis.modules.account.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sahaplus.baascore.bankone_apis.util.BaseResponse;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class AccountByAccountNumberResponse extends BaseResponse {

    private Data data;

    public AccountByAccountNumberResponse() {
        super();
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @ToString
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("AvailableBalance")
        private String availableBalance;
        @JsonProperty("WithdrawableBalance")
        private String withdrawableBalance;
        @JsonProperty("NUBAN")
        private String nuban;
        @JsonProperty("AccountTier")
        private String accountTier;
    }
}
