package com.sahaplus.baascore.bankone_apis.modules.account.dto.response;

import com.sahaplus.baascore.bankone_apis.util.BaseResponse;
import com.sahaplus.baascore.bankone_apis.util.Page;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class AddAccountToCustomerResponse extends BaseResponse {
    private Data data;

    public AddAccountToCustomerResponse() {
        super();
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @ToString
    @AllArgsConstructor
    public static class Data {
        private boolean isSuccessful;
        private String customerIDInString;
        private String message;
        private String transactionTrackingRef;
        private Page page;
    }
}
