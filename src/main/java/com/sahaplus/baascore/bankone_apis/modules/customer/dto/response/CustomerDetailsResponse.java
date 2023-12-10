package com.sahaplus.baascore.bankone_apis.modules.customer.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sahaplus.baascore.bankone_apis.enums.Gender;
import com.sahaplus.baascore.bankone_apis.util.BaseResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class CustomerDetailsResponse extends BaseResponse {
    private Data data;

    public CustomerDetailsResponse() {
        super();
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("Id")
        private long id;

        @JsonProperty("customerID")
        private String customerId;

        @JsonProperty("LastName")
        private String lastName;

        private Gender gender;

        @JsonProperty("genderString")
        private String genderString;

        @JsonProperty("OtherNames")
        private String otherNames;

        @JsonProperty("Address")
        private String address;

        @JsonProperty("Email")
        private String email;

        @JsonProperty("Landmark")
        private String landmark;

        @JsonProperty("PhoneNumber")
        private String phoneNumber;

        @JsonProperty("NickName")
        private String nickName;

        @JsonProperty("BankVerificationNumber")
        private String bankVerificationNumber;

        @JsonProperty("EmailNotification")
        private boolean emailNotification;

        @JsonProperty("PhoneNotification")
        private boolean phoneNotification;

        @JsonProperty("DistrictOfResidence")
        private String districtOfResidence;

        @JsonProperty("DistrictOfBusiness")
        private String districtOfBusiness;

        @JsonProperty("IdentificationNumber")
        private String identificationNumber;

        @JsonProperty("DateOfBirth")
        private LocalDateTime dateOfBirth;

        @JsonProperty("HomeTown")
        private String homeTown;

        @JsonProperty("LocalGovernment")
        private String localGovernment;

        @JsonProperty("MothersMaidenName")
        private String mothersMaidenName;

        @JsonProperty("Nationale")
        private String nationale;

        @JsonProperty("TestQuestion")
        private String testQuestion;

        @JsonProperty("TestAnswer")
        private String testAnswer;

        @JsonProperty("State")
        private String state;

        @JsonProperty("WorkAddress")
        private String workAddress;

        @JsonProperty("WorkAnnualIncome")
        private int workAnnualIncome;

        @JsonProperty("WorkEmployerName")
        private String workEmployerName;

        @JsonProperty("WorkNextOfKin")
        private String workNextOfKin;

        @JsonProperty("WorkNextOfKinAddress")
        private String workNextOfKinAddress;

        @JsonProperty("WorkNextOfKinPhoneNo")
        private String workNextOfKinPhoneNo;

        @JsonProperty("WorkNextOfKinRelationship")
        private String workNextOfKinRelationship;

        @JsonProperty("WorkOccupation")
        private String workOccupation;

        @JsonProperty("WorkPhoneNo")
        private String workPhoneNo;

        @JsonProperty("NameOnUtilityBill")
        private String nameOnUtilityBill;

        @JsonProperty("AddressOnUtilityBill")
        private String addressOnUtilityBill;

        @JsonProperty("DateOnUtilityBill")
        private LocalDateTime dateOnUtilityBill;

        @JsonProperty("CommentsForUtilityBill")
        private String commentsForUtilityBill;

        @JsonProperty("IsUtilityBillInfoComplete")
        private boolean isUtilityBillInfoComplete;

        @JsonProperty("NextOfKinEmailAddress")
        private String nextOfKinEmailAddress;

        @JsonProperty("CustomerPhoto")
        private String customerPhoto;

        @JsonProperty("CustomerSignature")
        private String customerSignature;

        @JsonProperty("Accounts")
        private List<Account> accounts;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("PostalAddress")
        private String postalAddress;

        @JsonProperty("BusinessPhoneNo")
        private String businessPhoneNo;

        @JsonProperty("TaxIDNo")
        private String taxIdNo;

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
    }


    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Account {
        @JsonProperty("AccessLevel")
        private String AccessLevel;

        @JsonProperty("AccountNumber")
        private String AccountNumber;

        @JsonProperty("AccountStatus")
        private String AccountStatus;

        @JsonProperty("AccountType")
        private String AccountType;

        @JsonProperty("AccountBalance")
        private String AccountBalance;

        @JsonProperty("Branch")
        private String Branch;

        @JsonProperty("CustomerID")
        private String CustomerId;

        @JsonProperty("CustomerName")
        private String CustomerName;

        @JsonProperty("DateCreated")
        private String DateCreated;

        @JsonProperty("LastActivityDate")
        private String LastActivityDate;

        @JsonProperty("NUBAN")
        private String NUBAN;

        @JsonProperty("Refree1CustomerID")
        private String Refree1CustomerID;

        @JsonProperty("Refree2CustomerID")
        private String Refree2CustomerID;

        @JsonProperty("ReferenceNo")
        private String ReferenceNo;
    }
}


