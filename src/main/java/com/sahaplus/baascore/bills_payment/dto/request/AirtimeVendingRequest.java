package com.sahaplus.baascore.bills_payment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirtimeVendingRequest {
    private String serviceCode;
    private String phone;
    private String amount;
    private String vend_type;
    private String network;
    private String request_id;
}
