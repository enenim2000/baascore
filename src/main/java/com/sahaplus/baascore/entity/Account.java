package com.sahaplus.baascore.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Account extends BaseClass {
    @Column(unique = true)
    private String accountNumber;
    private int bankOneAccountId;

    @Column(unique = true, length = 36)
    private String transactionTrackingRef;

    @Column(unique = true, length = 36)
    private String accountOpeningTrackingRef;

    private String accessLevel;
    private String accountStatus;
    private String accountType;
    private String availableBalance;
    private String branch;
    private String customerID;
    private String customerName;
    private String dateCreated;
    private String lastActivityDate;
    private String NUBAN;
    private String refree1CustomerID;
    private String refree2CustomerID;
    private String referenceNo;
    private boolean PNDStatus;
    private String accountTier;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User accountOwner;
}




