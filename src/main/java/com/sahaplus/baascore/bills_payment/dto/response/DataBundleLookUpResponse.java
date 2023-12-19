package com.sahaplus.baascore.bills_payment.dto.response;

import com.sahaplus.baascore.bills_payment.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataBundleLookUpResponse {
    private String message;
    private String status;
    private List<Product> product;
    private String phone;
    private String network;
}
