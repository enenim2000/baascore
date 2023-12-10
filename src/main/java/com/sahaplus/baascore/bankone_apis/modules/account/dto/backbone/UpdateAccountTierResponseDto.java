package com.sahaplus.baascore.bankone_apis.modules.account.dto.backbone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UpdateAccountTierResponseDto {
    @JsonProperty("ResponseCode")
    private String responseCode;
    @JsonProperty("ResponseMessage")
    private String responseMessage;
}
