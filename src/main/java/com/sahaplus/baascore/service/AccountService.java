package com.sahaplus.baascore.service;

import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.AccountByAccountNumberResponse;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.AccountsByCustomerIdResponse;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.OutstandingBalanceResponse;
import com.sahaplus.baascore.dto.request.AccountCreationRequest;
import com.sahaplus.baascore.dto.request.OnboardExistingCustomerRequest;
import com.sahaplus.baascore.dto.request.OnboardExistingCustomerResponse;
import com.sahaplus.baascore.dto.response.AccountCreationResponse;
import jakarta.validation.constraints.NotNull;

import java.lang.reflect.InvocationTargetException;

public interface AccountService {
    AccountCreationResponse accountOpeningForNewCustomersIndividual(AccountCreationRequest accountOpeningRequest) throws InvocationTargetException, IllegalAccessException;

    OnboardExistingCustomerResponse onBoardingExistingCustomers(OnboardExistingCustomerRequest onboardExistingCustomerRequest);

    AccountByAccountNumberResponse getAccountByAccountNumber(String accountNumber);

    OutstandingBalanceResponse getOutstandingBalance(@NotNull String accountNumber, String mfbCode);

    AccountsByCustomerIdResponse getAccountsByCustomerId(String customerId);
}
