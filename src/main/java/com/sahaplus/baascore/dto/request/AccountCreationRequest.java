package com.sahaplus.baascore.dto.request;

import com.sahaplus.baascore.bankone_apis.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountCreationRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String otherNames;
    @NotNull
    @Size(min = 11, max = 11)
    private String bvn;
    private String phoneNo;
    private Gender gender;
    private String placeOfBirth;
    private LocalDateTime dateOfBirth;
    private int streetNo;
    private String streetName;
    private String city;
    private String state;
    private String country;
    private String nationalIdentityNo;
    private String accountOfficerCode;
    private String bvnToken;
    private String loginId;
}
