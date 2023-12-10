package com.sahaplus.baascore.bankone_apis.modules.account.dto.backbone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class AccountByAccountNumberResponseDto {
    @JsonProperty("AvailableBalance")
    private String availableBalance;
    @JsonProperty("WithdrawableBalance")
    private String withdrawableBalance;
    @JsonProperty("CustomerID")
    private String customerId;
    @JsonProperty("NUBAN")
    private String nuban;
    @JsonProperty("AccountTier")
    private String accountTier;
}
