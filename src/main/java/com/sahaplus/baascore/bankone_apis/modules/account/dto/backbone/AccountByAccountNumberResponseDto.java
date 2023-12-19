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
    private String AvailableBalance;
    private String LedgerBalance;
    private String WithdrawableBalance;
    private String AccountType;
}
