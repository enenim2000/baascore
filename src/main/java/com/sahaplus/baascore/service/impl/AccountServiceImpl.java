package com.sahaplus.baascore.service.impl;

import com.sahaplus.baascore.auth.RequestUtil;
import com.sahaplus.baascore.bankone_apis.enums.CustomerType;
import com.sahaplus.baascore.bankone_apis.modules.account.BankOneAccountService;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.request.CreateAccountRequest;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.AccountByAccountNumberResponse;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.AccountsByCustomerIdResponse;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.CreateAccountResponse;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.OutstandingBalanceResponse;
import com.sahaplus.baascore.bankone_apis.modules.customer.BankOneCustomerService;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.request.CreateCustomerRequest;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.response.CreateCustomerResponse;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.response.CustomerDetailsResponse;
import com.sahaplus.baascore.dto.request.AccountCreationRequest;
import com.sahaplus.baascore.dto.request.OnboardExistingCustomerRequest;
import com.sahaplus.baascore.dto.request.OnboardExistingCustomerResponse;
import com.sahaplus.baascore.dto.response.AccountCreationResponse;
import com.sahaplus.baascore.entity.Account;
import com.sahaplus.baascore.entity.User;
import com.sahaplus.baascore.enums.AccountTier;
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
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        if (!RequestUtil.getAuthToken().isEmailVerified()) {
            throw new ApiException("Customer email is not verified");
        }
        if (!RequestUtil.getAuthToken().isPhoneVerified()) {
            throw new ApiException("Customer phone number is not verified");
        }
//        if (user.isBlocked()) {
//            throw new ApiException("Customer account is blocked");
//        }
//        if (user.isDeleted()) {
//            throw new ApiException("Customer account is deleted");
//        }
        log.trace("Initial Validations passed");

        boolean verified = bvnValidator.validateBvn(accountOpeningRequest.getBvnToken(), accountOpeningRequest.getNationalIdentityNo());
        if (!verified) {
            log.error("BVN cannot verified");
            throw new ApiException("BVN cannot verified");
        }
        log.trace("BVN successfully verified");

        User dbUser = userRepository.findByLoginId(RequestUtil.getAuthToken().getUuid()).orElse(null);
        System.out.println(dbUser);

        if (Objects.requireNonNull(dbUser).getBankOneCustomerId() != null) {
            log.error("Customer profile already exists on Bank One");
            throw new ApiException("Customer profile already exists on Bank One");
        } else {
            String accountOfficerCode = "0001";
            var createCustomerRequest = CreateCustomerRequest.builder()
                    .firstName(dbUser.getFirstName())
                    .gender(dbUser.getGender())
                    .email(RequestUtil.getAuthToken().getEmail())
                    .dateOfBirth(dbUser.getDateOfBirth())
                    .bankVerificationNumber(accountOpeningRequest.getBvn())
                    .accountOfficerCode(accountOfficerCode)
                    .lastName(dbUser.getLastName())
                    .otherNames(dbUser.getOtherNames())
                    .city(accountOpeningRequest.getCity())
                    .address(accountOpeningRequest.getStreetNo() + " " + accountOpeningRequest.getStreetName()
                            + " " + accountOpeningRequest.getCity() + " " + accountOpeningRequest.getState()
                            + " " + accountOpeningRequest.getCountry())
                    .phoneNo(RequestUtil.getAuthToken().getPhone())
                    .placeOfBirth(accountOpeningRequest.getPlaceOfBirth())
                    .nationalIdentityNo(dbUser.getNin())
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
                        .accountOfficerCode(accountOfficerCode)
                        .accountOpeningTrackingRef(accountOpeningTrackingRef)
                        .transactionTrackingRef(transTrackingRef)
                        .productCode(bankOneProductCodes.getPRODUCT_CODE_INDIVIDUAL_SAVINGS())
                        .lastName(dbUser.getLastName())
                        .otherNames(dbUser.getOtherNames())
                        .fullName(dbUser.getFirstName() + " " + dbUser.getLastName() + " " + dbUser.getOtherNames())
                        .phoneNo(RequestUtil.getAuthToken().getPhone())
                        .gender(dbUser.getGender())
                        .placeOfBirth(accountOpeningRequest.getPlaceOfBirth())
                        .dateOfBirth(dbUser.getDateOfBirth())
                        .accountTier(AccountTier.TIER1.getValue())
                        .email(RequestUtil.getAuthToken().getEmail())
                        .build();
                log.info("CreateAccountRequest: {}", accountCreationRequest);

                CreateAccountResponse newAccount = bankOneAccountService.createAccount(accountCreationRequest);

                if (!newAccount.getData().isSuccessful()) {
                    log.error("Account creation failed");
                    throw new ApiException("Account creation failed");
                }
                log.trace("Account creation successful");

                dbUser.setBankOneCustomerId(newCustomer.getData().getCustomerID());
                dbUser.setBankOneId(newCustomer.getData().getCustomerID());
                dbUser.setAddress(accountOpeningRequest.getStreetNo() + " " + accountOpeningRequest.getStreetName()
                        + " " + accountOpeningRequest.getCity() + " " + accountOpeningRequest.getState()
                        + " " + accountOpeningRequest.getCountry());
                userRepository.save(dbUser);

                var account = Account.builder()
                        .transactionTrackingRef(newAccount.getData().getTransactionTrackingRef())
                        .accountOpeningTrackingRef(accountOpeningTrackingRef)
                        .customerID(newCustomer.getData().getCustomerID())
                        .accountNumber(newAccount.getData().getMessage().getAccountNumber())
                        .customerName(newAccount.getData().getMessage().getFullName())
                        .bankOneAccountId(newAccount.getData().getMessage().getId())
                        .accountOwner(dbUser)
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
    public OnboardExistingCustomerResponse onBoardingExistingCustomers(OnboardExistingCustomerRequest onboardExistingCustomerRequest) {
        log.info("Onboard Existing Customer Request: {}", onboardExistingCustomerRequest);


        if (!RequestUtil.getAuthToken().isEmailVerified()) {
            throw new ApiException("Customer email is not verified");
        }
        if (!RequestUtil.getAuthToken().isPhoneVerified()) {
            throw new ApiException("Customer phone number is not verified");
        }
//        if (user.isBlocked()) {
//            throw new ApiException("Customer account is blocked");
//        }
//        if (user.isDeleted()) {
//            throw new ApiException("Customer account is deleted");
//        }
        log.trace("Initial Validations passed");

        boolean verified = bvnValidator.validateBvn(onboardExistingCustomerRequest.getBvnToken(), onboardExistingCustomerRequest.getNin());
        if (!verified) {
            log.error("BVN cannot verified");
            throw new ApiException("BVN cannot verified");
        }
        log.trace("BVN successfully verified");

        User dbUser = userRepository.findByLoginId(RequestUtil.getAuthToken().getUuid()).orElse(null);
        log.info("Db User {}", dbUser);

        if (Objects.requireNonNull(dbUser).getBankOneCustomerId() != null) {
            log.error("Customer is already onboard");
            throw new ApiException("Customer is already onboard");
        } else {
            CustomerDetailsResponse existingCustomer = bankOneCustomerService
                    .getCustomerDetailsByCustomerAccountNumber(onboardExistingCustomerRequest.getAccountNumber());

            if (!existingCustomer.getResponseCode().equals("00")) {
                log.error("Unable to get customer details");
                throw new ApiException("Unable to get customer details");
            }

            //TODO: Send otp to customers phone number and verify token

            dbUser.setOtherNames(existingCustomer.getData().getOtherNames());
            dbUser.setLastName(existingCustomer.getData().getLastName());
            dbUser.setBankOneCustomerId(existingCustomer.getData().getCustomerId());
            dbUser.setEmail(existingCustomer.getData().getEmail());
            dbUser.setMobile_phone(existingCustomer.getData().getPhoneNumber());
            dbUser.setGender(existingCustomer.getData().getGender());
            dbUser.setDateOfBirth(existingCustomer.getData().getDateOfBirth().toString());

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
                        .accountOwner(dbUser)
                        .build();
                existingUserAccounts.add(newaccount);
            }
            dbUser.setAccounts(existingUserAccounts);
            userRepository.save(dbUser);

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

