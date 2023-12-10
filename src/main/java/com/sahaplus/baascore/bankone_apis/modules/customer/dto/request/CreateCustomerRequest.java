package com.sahaplus.baascore.bankone_apis.modules.customer.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sahaplus.baascore.bankone_apis.enums.CustomerType;
import com.sahaplus.baascore.bankone_apis.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {

    @NotNull
    @JsonProperty("FirstName")
    private String firstName;
    @NotNull
    @JsonProperty("Gender")
    private Gender gender;
    @Email
    @NotNull
    @JsonProperty("Email")
    private String email;
    @NotNull
    @JsonProperty("DateOfBirth")
    private String dateOfBirth;
    @NotNull
    @JsonProperty("BankVerificationNumber")
    private String bankVerificationNumber;
    @NotNull
    @JsonProperty("AccountOfficerCode")
    private String accountOfficerCode;
    @JsonProperty("LastName")
    private String lastName;
    @JsonProperty("OtherNames")
    private String otherNames;
    @JsonProperty("City")
    private String city;
    @JsonProperty("Address")
    private String address;
    @JsonProperty("PhoneNo")
    private String phoneNo;
    @JsonProperty("PlaceOfBirth")
    private String placeOfBirth;
    @JsonProperty("NationalIdentityNo")
    private String nationalIdentityNo;
    @JsonProperty("NextOfKinName")
    private String nextOfKinName;
    @JsonProperty("NextOfKinPhoneNumber")
    private String nextOfKinPhoneNumber;
    @JsonProperty("ReferralName")
    private String referralName;
    @JsonProperty("ReferralPhoneNo")
    private String referralPhoneNo;
    @JsonProperty("CustomerType")
    private CustomerType customerType;
    @JsonProperty("BranchID")
    private long branchID;
    @JsonProperty("HasCurrentRunningLoanAndNotDefaulting")
    private boolean hasCurrentRunningLoanAndNotDefaulting;
    @JsonProperty("HasDefaultedInAnyLoan")
    private boolean hasDefaultedInAnyLoan;
    @JsonProperty("HasNoOutStandingLoanAndNotDefaulting")
    private boolean hasNoOutstandingLoanAndNotDefaulting;
    @JsonProperty("HasCompleteDocumentation")
    private boolean hasCompleteDocumentation;
    @JsonProperty("customerPassportInBytes")
    private byte[] customerPassportInBytes;

    private String version;
}
