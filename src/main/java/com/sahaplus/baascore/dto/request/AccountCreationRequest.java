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
    @Size(min = 11, max = 11)
    private String bvn;
    @NotNull
    private String placeOfBirth;
    private String streetNo = "";
    private String streetName = "";
    private String city = "";
    private String state = "";;
    private String country = "";;
    private String loginId;
}
