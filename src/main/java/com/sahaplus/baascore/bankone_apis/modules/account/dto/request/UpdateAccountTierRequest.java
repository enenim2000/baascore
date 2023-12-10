package com.sahaplus.baascore.bankone_apis.modules.account.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sahaplus.baascore.enums.AccountTier;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UpdateAccountTierRequest {
    @JsonProperty("AccountNumber")
    private String accountNumber;

    @JsonProperty("AccountTier")
    private AccountTier accountTier;

    @JsonProperty("SkipAddressVerification")
    private boolean skipAddressVerification = true;
}
