package com.sahaplus.baascore.bankone_apis.modules.account.dto.backbone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sahaplus.baascore.bankone_apis.util.Page;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class AddAccountToCustomerResponseDto {
    @JsonProperty("IsSuccessful")
    private boolean isSuccessful;
    @JsonProperty("CustomerIDInString")
    private String customerIDInString;
    @JsonProperty("Message")
    private Object message;
    @JsonProperty("TransactionTrackingRef")
    private String transactionTrackingRef;
    @JsonProperty("Page")
    private Page page;
}

