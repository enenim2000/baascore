package com.sahaplus.baascore.bankone_apis.modules.account.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class AddAccountToCustomerRequest {
    @NotNull
    @JsonProperty("TransactionTrackingRef")
    private String transactionTrackingRef;

    @NotNull
    @JsonProperty("AccountOpeningTrackingRef")
    private String accountOpeningTrackingRef;
    @NotNull
    @JsonProperty("CustomerID")
    private String customerID;

    @NotNull
    @JsonProperty("ProductCode")
    private String productCode;

    @NotNull
    @JsonProperty("Email")
    private String email;

    @NotNull
    @JsonProperty("BVN")
    private String bvn;

    @JsonProperty("AccountName")
    private String accountName;

    @JsonProperty("AccountOfficerCode")
    private String accountOfficerCode;

    private String version;
}
