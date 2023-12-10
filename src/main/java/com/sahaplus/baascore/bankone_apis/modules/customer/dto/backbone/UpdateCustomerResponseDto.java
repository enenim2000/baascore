package com.sahaplus.baascore.bankone_apis.modules.customer.dto.backbone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sahaplus.baascore.bankone_apis.modules.customer.Page;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerResponseDto {
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
