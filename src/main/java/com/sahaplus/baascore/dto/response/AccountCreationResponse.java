package com.sahaplus.baascore.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountCreationResponse {
    private String transactionTrackingRef;
    private String accountNumber;
    private String accountStatus;
    private String accountType;
    private String availableBalance;
    private String withdrawableBalance;
    private String customerID;
    private String customerName;
    private String dateCreated;
    private String nuban;
    private boolean pndStatus;
    private String accountTier;
    private String accountAccess;
}
