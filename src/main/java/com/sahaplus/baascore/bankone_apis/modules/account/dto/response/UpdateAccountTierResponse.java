package com.sahaplus.baascore.bankone_apis.modules.account.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sahaplus.baascore.bankone_apis.util.BaseResponse;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class UpdateAccountTierResponse extends BaseResponse {
    private Data data;

    public UpdateAccountTierResponse() {
        super();
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @ToString
    @AllArgsConstructor
    public static class Data {
        @JsonProperty("ResponseCode")
        private String responseCode;
        @JsonProperty("ResponseMessage")
        private String responseMessage;
    }
}
