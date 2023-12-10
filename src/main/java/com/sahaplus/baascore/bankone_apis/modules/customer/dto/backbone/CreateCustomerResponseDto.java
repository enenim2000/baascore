package com.sahaplus.baascore.bankone_apis.modules.customer.dto.backbone;

import com.sahaplus.baascore.bankone_apis.enums.CustomerType;
import com.sahaplus.baascore.bankone_apis.enums.Gender;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerResponseDto {
    private String LastName;
    private String OtherNames;
    private String Address;
    private Gender Gender;
    private LocalDateTime DateOfBirth;
    private String PhoneNo;
    private String NationalIdentityNo;
    private CustomerType CustomerType;
    private String BankVerificationNumber;
    private String CustomerID;
    private String Email;
}
