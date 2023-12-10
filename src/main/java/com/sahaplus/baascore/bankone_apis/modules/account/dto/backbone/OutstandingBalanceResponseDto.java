package com.sahaplus.baascore.bankone_apis.modules.account.dto.backbone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class OutstandingBalanceResponseDto {
    @JsonProperty("MFBCode")
    private String mfbCode;
    @JsonProperty("AccountNumber")
    private String accountNumber;
    @JsonProperty("AvailableBalance")
    private String availableBalance;
    @JsonProperty("FinancialDate")
    private LocalDateTime financialDate;
}
