package com.sahaplus.baascore.service.impl;

import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.AccountByAccountNumberResponse;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.AccountsByCustomerIdResponse;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.OutstandingBalanceResponse;
import com.sahaplus.baascore.dto.request.AccountCreationRequest;
import com.sahaplus.baascore.dto.request.OnboardExistingCustomerResponse;
import com.sahaplus.baascore.dto.response.AccountCreationResponse;
import com.sahaplus.baascore.bankone_apis.enums.CustomerType;
import com.sahaplus.baascore.bankone_apis.modules.account.BankOneAccountService;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.request.CreateAccountRequest;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.CreateAccountResponse;
import com.sahaplus.baascore.bankone_apis.modules.customer.BankOneCustomerService;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.request.CreateCustomerRequest;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.response.CreateCustomerResponse;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.response.CustomerDetailsResponse;
import com.sahaplus.baascore.entity.Account;
import com.sahaplus.baascore.entity.User;
import com.sahaplus.baascore.exception.ApiException;
import com.sahaplus.baascore.repository.AccountRepository;
import com.sahaplus.baascore.repository.UserRepository;
import com.sahaplus.baascore.service.AccountService;
import com.sahaplus.baascore.util.BVNValidator;
import com.sahaplus.baascore.util.BankOneProductCodes;
import com.sahaplus.baascore.util.HelperUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

//@SuppressWarnings("unused")
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final BankOneAccountService bankOneAccountService;
    private final BankOneProductCodes bankOneProductCodes;
    private final BankOneCustomerService bankOneCustomerService;
    private final HelperUtil helperUtil;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    private final BVNValidator bvnValidator;

    public AccountServiceImpl(BankOneAccountService bankOneAccountService, BankOneProductCodes bankOneProductCodes, BankOneCustomerService bankOneCustomerService, HelperUtil helperUtil, UserRepository userRepository, AccountRepository accountRepository, BVNValidator bvnValidator) {
        this.bankOneAccountService = bankOneAccountService;
        this.bankOneProductCodes = bankOneProductCodes;
        this.bankOneCustomerService = bankOneCustomerService;
        this.helperUtil = helperUtil;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.bvnValidator = bvnValidator;
    }

    @Override
    @Transactional
    public AccountCreationResponse accountOpeningForNewCustomersIndividual(AccountCreationRequest accountOpeningRequest) throws InvocationTargetException, IllegalAccessException {
        log.info("AccountCreationRequest: {}", accountOpeningRequest);

        if (StringUtils.isAnyBlank(
                accountOpeningRequest.getFirstName(), accountOpeningRequest.getLastName(),
                accountOpeningRequest.getBvn()
        )) {
            throw new ApiException("One or more mandatory fields are missing or have invalid values");
        }

        //TODO Get user details from logged in user
        User user = userRepository.findByLoginId(accountOpeningRequest.getLoginId())
                .orElseThrow(() -> new ApiException("User not found")); //Todo get this user from logged in user
        log.info("User: {}", user);

        if (!user.isEmailVerified()) {
            throw new ApiException("Customer email is not verified");
        }
        if (!user.isPhoneVerified()) {
            throw new ApiException("Customer phone number is not verified");
        }
        if (user.isBlocked()) {
            throw new ApiException("Customer account is blocked");
        }
        if (user.isDeleted()) {
            throw new ApiException("Customer account is deleted");
        }
        log.trace("Initial Validations passed");

        if (!user.isBvnVerified()) {
            boolean verified = bvnValidator.validateBvn(accountOpeningRequest.getBvnToken(), accountOpeningRequest.getBvn());
            if (!verified) {
                log.error("BVN cannot verified");
                throw new ApiException("BVN cannot verified");
            }
            log.trace("BVN successfully verified");
        }
        log.trace("BVN successfully verified");

        if (user.getBankOneCustomerId() != null) {
            log.error("Customer profile already exists on Bank One");
            throw new ApiException("Customer profile already exists on Bank One");
        } else {
            var createCustomerRequest = CreateCustomerRequest.builder()
                    .firstName(accountOpeningRequest.getFirstName())
                    .gender(accountOpeningRequest.getGender())
                    .email(user.getEmail())
                    .dateOfBirth(accountOpeningRequest.getDateOfBirth().toString())
                    .bankVerificationNumber(accountOpeningRequest.getBvn())
                    .accountOfficerCode(accountOpeningRequest.getAccountOfficerCode())
                    .lastName(accountOpeningRequest.getLastName())
                    .otherNames(accountOpeningRequest.getOtherNames())
                    .city(accountOpeningRequest.getCity())
                    .address(accountOpeningRequest.getStreetNo() + " " + accountOpeningRequest.getStreetName()
                            + " " + accountOpeningRequest.getCity() + " " + accountOpeningRequest.getState()
                            + " " + accountOpeningRequest.getCountry())
                    .phoneNo(accountOpeningRequest.getPhoneNo())
                    .phoneNo(accountOpeningRequest.getPhoneNo())
                    .placeOfBirth(accountOpeningRequest.getPlaceOfBirth())
                    .nationalIdentityNo(accountOpeningRequest.getNationalIdentityNo())
                    .customerType(CustomerType.Individual)
                    .build();
            log.info("CreateCustomerRequest: {}", createCustomerRequest);

            CreateCustomerResponse newCustomer = bankOneCustomerService.createCustomer(createCustomerRequest);
            log.info("CreateCustomerResponse: {}", newCustomer);

            if (!newCustomer.getResponseCode().equals("00")) {
                throw new ApiException("Customer profile creation failed");
            } else {
                String transTrackingRef = helperUtil.generateTrackingRef();
                String accountOpeningTrackingRef = helperUtil.generateTrackingRef();
                var accountCreationRequest = CreateAccountRequest.builder()
                        .accountName(accountOpeningRequest.getFirstName() + " " + accountOpeningRequest.getLastName())
                        .bvn(accountOpeningRequest.getBvn())
                        .customerID(newCustomer.getData().getCustomerID())
                        .accountOfficerCode("0001")
                        .accountOpeningTrackingRef(accountOpeningTrackingRef)
                        .transactionTrackingRef(transTrackingRef)
                        .productCode(bankOneProductCodes.getPRODUCT_CODE_INDIVIDUAL_SAVINGS())
                        .lastName(accountOpeningRequest.getLastName())
                        .otherNames(accountOpeningRequest.getOtherNames())
                        .fullName(accountOpeningRequest.getFirstName() + " " + accountOpeningRequest.getLastName() + " " + accountOpeningRequest.getOtherNames())
                        .phoneNo(accountOpeningRequest.getPhoneNo())
                        .gender(accountOpeningRequest.getGender())
                        .placeOfBirth(accountOpeningRequest.getPlaceOfBirth())
                        .dateOfBirth(accountOpeningRequest.getDateOfBirth().toString())
                        .accountTier("1")
                        .email(user.getEmail())
                        .build();
                log.info("CreateAccountRequest: {}", accountCreationRequest);

                CreateAccountResponse newAccount = bankOneAccountService.createAccount(accountCreationRequest);

                if (!newAccount.getData().isSuccessful()) {
                    log.error("Account creation failed");
                    throw new ApiException("Account creation failed");
                }
                log.trace("Account creation successful");

                user.setBankOneCustomerId(newCustomer.getData().getCustomerID());
                user.setBankOneId(newCustomer.getData().getCustomerID());
                user.setFirstName(accountOpeningRequest.getFirstName());
                user.setLastName(accountOpeningRequest.getLastName());
                user.setOtherNames(accountOpeningRequest.getOtherNames());
                user.setGender(accountOpeningRequest.getGender());
                user.setMobile_phone(accountOpeningRequest.getPhoneNo());
                user.setDateOfBirth(accountOpeningRequest.getDateOfBirth());
                user.setNin(accountOpeningRequest.getNationalIdentityNo());
                userRepository.save(user);

                var account = Account.builder()
                        .transactionTrackingRef(newAccount.getData().getTransactionTrackingRef())
                        .accountOpeningTrackingRef(accountOpeningTrackingRef)
                        .customerID(newCustomer.getData().getCustomerID())
                        .accountNumber(newAccount.getData().getMessage().getAccountNumber())
                        .customerName(newAccount.getData().getMessage().getFullName())
                        .bankOneAccountId(newAccount.getData().getMessage().getId())
                        .accountOwner(user)
                        .build();
                accountRepository.save(account);

                var finalResponse = AccountCreationResponse.builder()
                        .transactionTrackingRef(transTrackingRef)
                        .accountNumber(newAccount.getData().getMessage().getAccountNumber())
                        .customerID(newAccount.getData().getMessage().getCustomerID())
                        .customerName(newAccount.getData().getMessage().getFullName())
                        .build();
                log.info("AccountCreationResponse: {}", finalResponse);

                return finalResponse;
            }
        }
    }

    @Override
    public OnboardExistingCustomerResponse onBoardingExistingCustomers(String accountNumber, long loginId) {
        log.info("AccountCreationRequest: {}", accountNumber);

        if (StringUtils.isAnyBlank(accountNumber)) {
            throw new ApiException("Account Number Was Not Parsed");
        }
        if (StringUtils.isAnyBlank(String.valueOf(loginId))) {
            throw new ApiException("User login id Was Not Parsed");
        }

        //TODO Get user details from logged in user
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new ApiException("User not found")); //Todo get this user from logged in user

        log.info("User: {}", user);

        if (user.isBlocked()) {
            throw new ApiException("Customer account is blocked");
        }
        if (user.isDeleted()) {
            throw new ApiException("Customer account is deleted");
        }
        log.trace("Initial Validations passed");

        if (user.getBankOneCustomerId() != null) {
            log.error("Customer is already onboard");
            throw new ApiException("Customer is already onboard");
        } else {
            CustomerDetailsResponse existingCustomer = bankOneCustomerService.getCustomerDetailsByCustomerAccountNumber(accountNumber);

            if (!existingCustomer.getResponseCode().equals("00")) {
                log.error("Unable to get customer details");
                throw new ApiException("Unable to get customer details");
            }

            //TODO: Send otp to customers phone number and verify token

            user.setOtherNames(existingCustomer.getData().getOtherNames());
            user.setLastName(existingCustomer.getData().getLastName());
            user.setBankOneCustomerId(existingCustomer.getData().getCustomerId());
            user.setEmail(existingCustomer.getData().getEmail());
            user.setMobile_phone(existingCustomer.getData().getPhoneNumber());
            user.setGender(existingCustomer.getData().getGender());
            user.setDateOfBirth(existingCustomer.getData().getDateOfBirth());
            user.setEmailVerified(true);
            user.setPhoneVerified(true);
            user.setBvnVerified(true);

            List<Account> existingUserAccounts = new ArrayList<>();

            for (CustomerDetailsResponse.Account account : existingCustomer.getData().getAccounts()) {

                Account newaccount = Account.builder()
                        .accountNumber(account.getAccountNumber())
                        .customerName(account.getCustomerName())
                        .accessLevel(account.getAccessLevel())
                        .accountStatus(account.getAccountStatus())
                        .accountType(account.getAccountType())
                        .availableBalance(account.getAccountBalance())
                        .branch(account.getBranch())
                        .customerID(account.getCustomerId())
                        .dateCreated(account.getDateCreated())
                        .lastActivityDate(account.getLastActivityDate())
                        .NUBAN(account.getNUBAN())
                        .refree1CustomerID(account.getRefree1CustomerID())
                        .refree2CustomerID(account.getRefree2CustomerID())
                        .referenceNo(account.getReferenceNo())
                        .accountOwner(user)
                        .build();
                existingUserAccounts.add(newaccount);
            }
            user.setAccounts(existingUserAccounts);
            userRepository.save(user);

            return OnboardExistingCustomerResponse.builder()
                    .customerId(existingCustomer.getData().getCustomerId())
                    .build();
        }
    }

    @Override
    public AccountByAccountNumberResponse getAccountByAccountNumber(String accountNumber) {
        return bankOneAccountService.getAccountByAccountNumber(accountNumber);
    }

    @Override
    public OutstandingBalanceResponse getOutstandingBalance(@NotNull String accountNumber, String mfbCode) {
        return bankOneAccountService.getOutStandingBalance(accountNumber, mfbCode);
    }

    @Override
    public AccountsByCustomerIdResponse getAccountsByCustomerId(String customerId) {
        return bankOneAccountService.getAccountsByCustomerId(customerId);
    }
}

