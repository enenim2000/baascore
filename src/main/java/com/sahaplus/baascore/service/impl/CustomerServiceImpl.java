package com.sahaplus.baascore.service.impl;

import com.sahaplus.baascore.bankone_apis.modules.customer.BankOneCustomerService;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.response.CustomerDetailsResponse;
import com.sahaplus.baascore.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final BankOneCustomerService bankOneCustomerService;


    public CustomerServiceImpl(BankOneCustomerService bankOneCustomerService) {
        this.bankOneCustomerService = bankOneCustomerService;
    }

    @Override
    public CustomerDetailsResponse getCustomerDetailsByCustomerId(String customerId) {
        return bankOneCustomerService.getCustomerDetailsByCustomerId(customerId);
    }
}
