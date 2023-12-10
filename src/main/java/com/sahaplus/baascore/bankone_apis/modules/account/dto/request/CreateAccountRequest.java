package com.sahaplus.baascore.bankone_apis.modules.account.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sahaplus.baascore.bankone_apis.enums.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CreateAccountRequest {
    @NotNull
    @JsonProperty("TransactionTrackingRef")
    private String transactionTrackingRef;

    @JsonProperty("CustomerID")
    private String customerID;

    @JsonProperty("AccountReferenceNumber")
    private String accountReferenceNumber;

    @JsonProperty("AccountOpeningTrackingRef")
    private String accountOpeningTrackingRef;

    @NotNull
    @JsonProperty("ProductCode")
    private String productCode;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("OtherNames")
    private String otherNames;

    @JsonProperty("AccountName")
    private String accountName;

    @JsonProperty("BVN")
    private String bvn;

    @JsonProperty("FullName")
    private String fullName;

    @JsonProperty("PhoneNo")
    private String phoneNo;

    @NotNull
    @JsonProperty("Gender")
    private Gender gender;

    @JsonProperty("PlaceOfBirth")
    private String placeOfBirth;

    @JsonProperty("DateOfBirth")
    private String dateOfBirth;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("NationalIdentityNo")
    private String nationalIdentityNo;

    @JsonProperty("NationalIdentityType")
    private String nationalIdentityType;

    @JsonProperty("NextOfKinPhoneNo")
    private String nextOfKinPhoneNo;

    @JsonProperty("NextOfKinName")
    private String nextOfKinName;

    @JsonProperty("ReferralPhoneNo")
    private String referralPhoneNo;

    @JsonProperty("referralName")
    private String referralName;

    @JsonProperty("HasSufficientInfoOnAccountInfo")
    private boolean hasSufficientInfoOnAccountInfo;

    @JsonProperty("AccountInformationSource")
    private int accountInformationSource = 0;

    @JsonProperty("OtherAccountInformationSource")
    private String otherAccountInformationSource;

    @NotNull
    @JsonProperty("AccountOfficerCode")
    private String accountOfficerCode;

    @JsonProperty("AccountNumber")
    private String accountNumber;
    @NotNull
    @JsonProperty("Email")
    private String email;

    @JsonProperty("CustomerImage")
    private String customerImage;

    @JsonProperty("IdentificationImage")
    private String identificationImage;

    @JsonProperty("IdentificationImageType")
    private int identificationImageType = 0;

    @JsonProperty("CustomerSignature")
    private String customerSignature;

    @JsonProperty("NotificationPreference")
    private int notificationPreference = 0;

    @JsonProperty("TransactionPermission")
    private int transactionPermission = 0;

    @JsonProperty("AccountTier")
    private String accountTier;
}
