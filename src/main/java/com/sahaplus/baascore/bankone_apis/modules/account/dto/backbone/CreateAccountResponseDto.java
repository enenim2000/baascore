package com.sahaplus.baascore.bankone_apis.modules.account.dto.backbone;

import com.sahaplus.baascore.bankone_apis.util.Page;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CreateAccountResponseDto {
    private boolean IsSuccessful;
    private String CustomerIDInString;
    private CreateAccountResponseMessage Message;
    private String TransactionTrackingRef;
    private Page Page;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class CreateAccountResponseMessage {
        private String AccountNumber;
        private String BankoneAccountNumber;
        private String CustomerID;
        private String FullName;
        private Object CreationMessage;
        private int Id;
    }
}

