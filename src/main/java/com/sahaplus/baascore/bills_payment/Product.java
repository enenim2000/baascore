package com.sahaplus.baascore.bills_payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private double price;
    private String code;
    private String allowance;
    private String validity;
}
