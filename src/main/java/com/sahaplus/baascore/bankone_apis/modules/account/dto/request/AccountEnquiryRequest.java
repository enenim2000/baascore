package com.sahaplus.baascore.bankone_apis.modules.account.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountEnquiryRequest {

    @JsonProperty("AccountNo")
    private String AccountNo;

    @JsonProperty("AuthenticationCode")
    private String AuthenticationCode;
}
