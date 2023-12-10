package com.sahaplus.baascore.bankone_apis.modules.account.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CreateSavingsAccountRequest {
    @JsonProperty("AccountNumber")
    private String accountNumber;
    @JsonProperty("AccountOpeningTrackingRef")
    private String accountOpeningTrackingRef;
    @JsonProperty("Amount")
    private BigDecimal amount;
    @JsonProperty("Narration")
    private String narration;
    @JsonProperty("AccountOfficerCode")
    private String accountOfficerCode;
    @JsonProperty("ProductCode")
    private String productCode;
    private String version;
}
