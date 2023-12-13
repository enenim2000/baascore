package com.sahaplus.baascore.bankone_apis.modules.customer.dto.backbone;

import com.sahaplus.baascore.bankone_apis.enums.Gender;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailsResponseDto {
    private long Id;

    private String customerID;

    private String LastName;

    private Gender Gender;

    private String GenderString;

    private String OtherNames;

    private String Address;

    private String Email;

    private String Landmark;

    private String PhoneNumber;

    private String NickName;

    private String BankVerificationNumber;

    private boolean EmailNotification;

    private boolean PhoneNotification;

    private String DistrictOfResidence;

    private String DistrictOfBusiness;

    private String IdentificationNumber;

    private LocalDateTime DateOfBirth;

    private String HomeTown;

    private String LocalGovernment;

    private String MothersMaidenName;

    private String Nationale;

    private String TestQuestion;

    private String TestAnswer;

    private String State;

    private String WorkAddress;

    private int WorkAnnualIncome;

    private String WorkEmployerName;

    private String WorkNextOfKin;

    private String WorkNextOfKinAddress;

    private String WorkNextOfKinPhoneNo;

    private String WorkNextOfKinRelationship;

    private String WorkOccupation;

    private String WorkPhoneNo;

    private String NameOnUtilityBill;

    private String AddressOnUtilityBill;

    private LocalDateTime DateOnUtilityBill;

    private String CommentsForUtilityBill;

    private boolean IsUtilityBillInfoComplete;

    private String NextOfKinEmailAddress;

    private String CustomerPhoto;

    private String CustomerSignature;

    private List<Account> Accounts;

    private String Name;

    private String PostalAddress;

    private String BusinessPhoneNo;

    private String TaxIdNo;

    private String BusinessName;

    private String TradeName;

    private String IndustrialSector;

    private LocalDateTime CompanyRegDate;

    private String ContactPersonName;

    private String BusinessType;

    private String BusinessNature;

    private String WebAddress;

    private LocalDateTime DateIncorporated;

    private LocalDateTime BusinessCommencementDate;

    private String RegistrationNumber;

    private List<String> CustomerMembers;

    private List<String> TheDirectors;


    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Account {
        private String AccessLevel;

        private String AccountNumber;

        private String AccountStatus;

        private String AccountType;

        private String AccountBalance;

        private String Branch;

        private String CustomerID;

        private String CustomerName;

        private String DateCreated;

        private String LastActivityDate;

        private String NUBAN;

        private String Refree1CustomerID;

        private String Refree2CustomerID;

        private String ReferenceNo;
    }
}


