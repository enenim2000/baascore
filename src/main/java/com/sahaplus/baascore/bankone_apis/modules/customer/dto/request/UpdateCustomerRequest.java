package com.sahaplus.baascore.bankone_apis.modules.customer.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sahaplus.baascore.bankone_apis.enums.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
    @NotNull
    @JsonProperty("Id")
    private String id;

    @NotNull
    @JsonProperty("customerID")
    private String customerId;
    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("Gender")
    private Gender gender;

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
    private String dateOfBirth;

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
    private String workAnnualIncome;

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
    private String dateOnUtilityBill;

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

    @JsonProperty("Name")
    private String name;

    @JsonProperty("PostalAddress")
    private String postalAddress;

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
    private String companyRegDate;

    @JsonProperty("ContactPersonName")
    private String contactPersonName;

    @JsonProperty("BusinessType")
    private String businessType;

    @JsonProperty("BusinessNature")
    private String businessNature;

    @JsonProperty("WebAddress")
    private String webAddress;

    @JsonProperty("DateIncorporated")
    private String dateIncorporated;

    @JsonProperty("BusinessCommencementDate")
    private String businessCommencementDate;

    @JsonProperty("RegistrationNumber")
    private String registrationNumber;

    @JsonProperty("CustomerMembers")
    private List<String> customerMembers;

    @JsonProperty("TheDirectors")
    private List<String> theDirectors;

    private String version;
}
