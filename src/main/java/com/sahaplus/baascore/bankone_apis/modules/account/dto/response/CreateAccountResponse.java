package com.sahaplus.baascore.bankone_apis.modules.account.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sahaplus.baascore.bankone_apis.util.BaseResponse;
import com.sahaplus.baascore.bankone_apis.util.Page;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class CreateAccountResponse extends BaseResponse {
    private Data data;

    public CreateAccountResponse() {
        super();
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @ToString
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("IsSuccessful")
        private boolean isSuccessful;
        @JsonProperty("CustomerIDInString")
        private String customerIDInString;
        @JsonProperty("Message")
        private CreateAccountResponse.CreateAccountResponseMessage message;
        @JsonProperty("TransactionTrackingRef")
        private String transactionTrackingRef;
        @JsonProperty("Page")
        private Page page;


    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class CreateAccountResponseMessage {
        @JsonProperty("AccountNumber")
        private String accountNumber;
        @JsonProperty("BankoneAccountNumber")
        private String bankoneAccountNumber;
        @JsonProperty("CustomerID")
        private String customerID;
        @JsonProperty("FullName")
        private String fullName;
        @JsonProperty("CreationMessage")
        private Object creationMessage;
        @JsonProperty("Id")
        private int id;
    }
}
