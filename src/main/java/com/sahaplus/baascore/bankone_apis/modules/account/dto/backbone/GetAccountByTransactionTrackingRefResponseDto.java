package com.sahaplus.baascore.bankone_apis.modules.account.dto.backbone;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class GetAccountByTransactionTrackingRefResponseDto {
    private String AccessLevel;
    private String AccountNumber;
    private String AccountStatus;
    private String AccountType;
    private String AvailableBalance;
    private String Branch;
    private String CustomerID;
    private String CustomerName;
    private String DateCreated;
    private String lastActivityDate;
    private String NUBAN;
    private String Refree1CustomerID;
    private String Refree2CustomerID;
    private String ReferenceNo;
    private boolean PNDStatus;
    private String AccountTier;
}
