package com.sahaplus.baascore.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountCreationResponse {
    private String transactionTrackingRef;
    private String accountNumber;

    private String customerID;
    private String customerName;
    private String dateCreated = LocalDateTime.now().toString();
    private boolean pndStatus;
}
