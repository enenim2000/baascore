package com.sahaplus.baascore.bills_payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirtimeVendingResponse {
    private String message;
    private String status;
    private double amount;
    private String transId;
    private String type;
    private String date;
    private String phone;
}
