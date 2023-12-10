package com.sahaplus.baascore.service;

import com.sahaplus.baascore.bankone_apis.modules.customer.dto.response.CustomerDetailsResponse;

public interface CustomerService {
    CustomerDetailsResponse getCustomerDetailsByCustomerId(String customerId);
}
