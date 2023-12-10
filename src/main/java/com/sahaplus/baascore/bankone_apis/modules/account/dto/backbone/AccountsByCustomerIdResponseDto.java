package com.sahaplus.baascore.bankone_apis.modules.account.dto.backbone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.backbone.CustomerDetailsResponseDto;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.response.CustomerDetailsResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class AccountsByCustomerIdResponseDto {
    private String address;
    private String age;
    private String BVN;
    private String branchCode;
    private String customerID;
    private String dateOfBirth;
    private String email;
    private String gender;
    private String localGovernmentArea;
    private String name;
    private String phoneNumber;
    private String state;
    @JsonProperty("PostalAddress")
    private String postalAddress;
    @JsonProperty("Accounts")
    private List<Account> accounts;
    @JsonProperty("BusinessPhoneNo")
    private String businessPhoneNo;
    @JsonProperty("TaxIDNo")
    private String taxIDNo;
    @JsonProperty("BusinessName")
    private String businessName;
    @JsonProperty("TradeName")
    private String tradeName;
    @JsonProperty("IndustrialSector")
    private String industrialSector;
    @JsonProperty("CompanyRegDate")
    private LocalDateTime companyRegDate;
    @JsonProperty("ContactPersonName")
    private String contactPersonName;
    @JsonProperty("BusinessType")
    private String businessType;
    @JsonProperty("BusinessNature")
    private String businessNature;
    @JsonProperty("WebAddress")
    private String webAddress;
    @JsonProperty("DateIncorporated")
    private LocalDateTime dateIncorporated;
    @JsonProperty("BusinessCommencementDate")
    private LocalDateTime businessCommencementDate;
    @JsonProperty("RegistrationNumber")
    private String registrationNumber;
    @JsonProperty("CustomerMembers")
    private List<String> customerMembers;
    @JsonProperty("TheDirectors")
    private List<String> theDirectors;

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Account {
        private String accessLevel;

        private String accountNumber;

        private String accountStatus;

        private String accountType;

        private String availableBalance;

        private String branch;

        private String customerID;

        private String accountName;

        private String productCode;

        private String dateCreated;

        private String lastActivityDate;

        private String ledgerBalance;

        private String NUBAN;

        private String referenceNo;

        private String withdrawableAmount;

        private String kycLevel;
    }
}


