package com.sahaplus.baascore.bankone_apis.modules.account.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sahaplus.baascore.bankone_apis.util.BaseResponse;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class OutstandingBalanceResponse extends BaseResponse {
    private Data data;

    public OutstandingBalanceResponse() {
        super();
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @ToString
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("MFBCode")
        private String mfbCode;
        @JsonProperty("AccountNumber")
        private String accountNumber;
        @JsonProperty("AvailableBalance")
        private String availableBalance;
        @JsonProperty("FinancialDate")
        private LocalDateTime financialDate;

    }


}
