package com.sahaplus.baascore.bills_payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataBundleLookUpRequest {
    private String serviceCode;
    private String phone;
    private String network;
}
