package com.sahaplus.baascore.bankone_apis.modules.customer.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sahaplus.baascore.bankone_apis.enums.CustomerType;
import com.sahaplus.baascore.bankone_apis.enums.Gender;
import com.sahaplus.baascore.bankone_apis.util.BaseResponse;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateCustomerResponse extends BaseResponse {
    private Data data;

    public CreateCustomerResponse() {
        super();
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("LastName")
        private String lastName;
        @JsonProperty("OtherNames")
        private String otherNames;
        @JsonProperty("Address")
        private String address;
        @JsonProperty("Gender")
        private Gender gender;
        @JsonProperty("DateOfBirth")
        private LocalDateTime dateOfBirth;
        @JsonProperty("PhoneNo")
        private String phoneNo;
        @JsonProperty("NationalIdentityNo")
        private String nationalIdentityNo;
        @JsonProperty("customerType")
        private CustomerType customerType;
        @JsonProperty("BankVerificationNumber")
        private String bankVerificationNumber;
        @JsonProperty("CustomerID")
        private String customerID;
        @JsonProperty("Email")
        private String email;
    }
}

