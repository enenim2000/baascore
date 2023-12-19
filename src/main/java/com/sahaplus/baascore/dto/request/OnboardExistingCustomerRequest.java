package com.sahaplus.baascore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnboardExistingCustomerRequest {
    private String accountNumber;
    private String loginId;
}
