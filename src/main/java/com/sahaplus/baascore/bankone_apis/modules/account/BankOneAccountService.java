package com.sahaplus.baascore.bankone_apis.modules.account;

import com.sahaplus.baascore.bankone_apis.modules.account.dto.backbone.*;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.request.*;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.*;
import com.sahaplus.baascore.bankone_apis.util.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BankOneAccountService {

    @Value("${bankone.thirdPartyBaseUrl}")
    private String thirdPartyBaseUrl;
    @Value("${bankone.baseurl}")
    private String baseUrl;

    @Value("${bankone.token}")
    private String authToken;
    private final HttpClient httpClient;

    public BankOneAccountService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        log.info("CREATE_ACCOUNT_REQUEST: {}", createAccountRequest);

        String apiUrl = baseUrl + "/api/Account/CreateAccountQuick/2" + "?authtoken=" + authToken;

        var apiResponse = httpClient.callApi(createAccountRequest, CreateAccountResponseDto.class, HttpMethod.POST,
                apiUrl);
        System.out.println(apiResponse.toString());

        CreateAccountResponse.CreateAccountResponseMessage message = new CreateAccountResponse.CreateAccountResponseMessage();
        message.setAccountNumber(apiResponse.getMessage().getAccountNumber());
        message.setBankoneAccountNumber(apiResponse.getMessage().getBankoneAccountNumber());
        message.setCustomerID(apiResponse.getMessage().getCustomerID());
        message.setFullName(apiResponse.getMessage().getFullName());
        message.setCreationMessage(apiResponse.getMessage().getCreationMessage());
        message.setId(apiResponse.getMessage().getId());

        CreateAccountResponse.Data data = new CreateAccountResponse.Data();
        data.setSuccessful(apiResponse.isIsSuccessful());
        data.setCustomerIDInString(apiResponse.getCustomerIDInString());
        data.setTransactionTrackingRef(apiResponse.getTransactionTrackingRef());
        data.setPage(apiResponse.getPage());
        data.setMessage(message);

        var response = new CreateAccountResponse();
        response.setData(data);

        log.info("CREATE_ACCOUNT_RESPONSE: {}", response);
        return response;
    }

    public UpdateAccountTierResponse updateAccountTier(UpdateAccountTierRequest updateAccountTierRequest) {
        log.info("UPDATE_ACCOUNT_TIER_REQUEST: {}", updateAccountTierRequest);

        String apiUrl = baseUrl + "/api/Account/UpdateAccountTier/2" + "?authtoken=" + authToken;

        var apiResponse = httpClient.callApi(updateAccountTierRequest, UpdateAccountTierResponseDto.class, HttpMethod.POST,
                apiUrl);
        var response = new UpdateAccountTierResponse();
        BeanUtils.copyProperties(response.getData(), apiResponse);
        log.info("UPDATE_ACCOUNT_TIER_RESPONSE: {}", response);
        return response;
    }

    public GetAccountByTransactionTrackingRefResponse getAccountByTransactionTrackingRef(String transactionTrackingRef) {
        log.info("BankOne-GetAccountByTransactionTrackingRefRequest: {}", transactionTrackingRef);

        String apiUrl = baseUrl + "/api/Account/GetAccountByTransactionTrackingRef/2" + "?authtoken=" + authToken + "&transactionTrackingRef=" + transactionTrackingRef;

        var apiResponse = httpClient.callApi(null, GetAccountByTransactionTrackingRefResponseDto.class, HttpMethod.GET,
                apiUrl);

        GetAccountByTransactionTrackingRefResponse.Data data = new GetAccountByTransactionTrackingRefResponse.Data();
        data.setAccountNumber(apiResponse.getAccountNumber());
        data.setAccountStatus(apiResponse.getAccountStatus());
        data.setAccountType(apiResponse.getAccountType());
        data.setAvailableBalance(apiResponse.getAvailableBalance());
        data.setBranch(apiResponse.getBranch());
        data.setCustomerID(apiResponse.getCustomerID());
        data.setCustomerName(apiResponse.getCustomerName());
        data.setDateCreated(apiResponse.getDateCreated());
        data.setLastActivityDate(apiResponse.getLastActivityDate());
        data.setNuban(apiResponse.getNUBAN());
        data.setRefree1CustomerID(apiResponse.getRefree1CustomerID());
        data.setRefree2CustomerID(apiResponse.getRefree2CustomerID());
        data.setReferenceNo(apiResponse.getReferenceNo());
        data.setPndStatus(apiResponse.isPNDStatus());
        data.setAccountTier(apiResponse.getAccountTier());

        var response = new GetAccountByTransactionTrackingRefResponse();
        response.setData(data);
        log.info("BankOne-CreateCustomerAndAccountResponse: {}", response);

        return response;
    }

    public AccountByAccountNumberResponse getAccountByAccountNumber(String accountNumber) {
        log.info("BankOne-GetAccountByAccountNumberRequest: {}", accountNumber);

        String apiUrl = baseUrl + "/api/Account/GetAccountByAccountNumber/2" + "?authtoken=" + authToken + "&accountNumber=" + accountNumber;

        var apiResponse = httpClient.callApi(null, AccountByAccountNumberResponseDto.class, HttpMethod.GET,
                apiUrl);

        AccountByAccountNumberResponse.Data data = new AccountByAccountNumberResponse.Data();
        data.setAvailableBalance(apiResponse.getAvailableBalance());
        data.setWithdrawableBalance(apiResponse.getWithdrawableBalance());
        data.setLedgerBalance(apiResponse.getLedgerBalance());
        data.setAccountType(apiResponse.getAccountType());

        var response = new AccountByAccountNumberResponse();
        response.setData(data);
        log.info("BankOne-GetAccountByAccountNumberResponse: {}", response);

        return response;
    }

    public AccountsByCustomerIdResponse getAccountsByCustomerId(String customerId) {
        log.info("BankOne-GetAccountByCustomerIdRequest: {}", customerId);

        String apiUrl = baseUrl + "/api/Account/GetAccountsByCustomerId/2" + "?authtoken=" + authToken + "&customerId=" + customerId;

        var apiResponse = httpClient.callApi(null, AccountsByCustomerIdResponseDto.class, HttpMethod.GET,
                apiUrl);
        System.out.println(apiResponse);
        System.out.println(apiResponse.getAccounts());

        List<AccountsByCustomerIdResponseDto.Account> accounts = new ArrayList<>();

        for (AccountsByCustomerIdResponseDto.Account account : apiResponse.getAccounts()) {
            AccountsByCustomerIdResponseDto.Account individualAccount = new AccountsByCustomerIdResponseDto.Account();
            individualAccount.setAccessLevel(account.getAccessLevel());
            individualAccount.setAccountNumber(account.getAccountNumber());
            individualAccount.setAccountStatus(account.getAccountStatus());
            individualAccount.setAccountType(account.getAccountType());
            individualAccount.setAvailableBalance(account.getAvailableBalance());
            individualAccount.setBranch(account.getBranch());
            individualAccount.setCustomerID(account.getCustomerID());
            individualAccount.setAccountName(account.getAccountName());
            individualAccount.setProductCode(account.getProductCode());
            individualAccount.setDateCreated(account.getDateCreated());
            individualAccount.setLastActivityDate(account.getLastActivityDate());
            individualAccount.setLedgerBalance(account.getLedgerBalance());
            individualAccount.setNUBAN(account.getNUBAN());
            individualAccount.setReferenceNo(account.getReferenceNo());
            individualAccount.setWithdrawableAmount(account.getWithdrawableAmount());
            individualAccount.setKycLevel(account.getKycLevel());
            accounts.add(individualAccount);
        }

        AccountsByCustomerIdResponse.Data data = new AccountsByCustomerIdResponse.Data();
        data.setAddress(apiResponse.getAddress());
        data.setAge(apiResponse.getAge());
        data.setBVN(apiResponse.getBVN());
        data.setBranchCode(apiResponse.getBranchCode());
        data.setCustomerID(apiResponse.getCustomerID());
        data.setDateOfBirth(apiResponse.getDateOfBirth());
        data.setEmail(apiResponse.getEmail());
        data.setGender(apiResponse.getGender());
        data.setLocalGovernmentArea(apiResponse.getLocalGovernmentArea());
        data.setName(apiResponse.getName());
        data.setPhoneNumber(apiResponse.getPhoneNumber());
        data.setState(apiResponse.getState());
        data.setPostalAddress(apiResponse.getPostalAddress());
        data.setAccounts(accounts);
        data.setBusinessPhoneNo(apiResponse.getBusinessPhoneNo());
        data.setTaxIDNo(apiResponse.getTaxIDNo());
        data.setBusinessName(apiResponse.getBusinessName());
        data.setTradeName(apiResponse.getTradeName());
        data.setIndustrialSector(apiResponse.getIndustrialSector());
        data.setCompanyRegDate(apiResponse.getCompanyRegDate());
        data.setContactPersonName(apiResponse.getContactPersonName());
        data.setBusinessType(apiResponse.getBusinessType());
        data.setBusinessNature(apiResponse.getBusinessNature());
        data.setWebAddress(apiResponse.getWebAddress());
        data.setDateIncorporated(apiResponse.getDateIncorporated());
        data.setBusinessCommencementDate(apiResponse.getBusinessCommencementDate());
        data.setRegistrationNumber(apiResponse.getRegistrationNumber());
        data.setCustomerMembers(apiResponse.getCustomerMembers());
        data.setTheDirectors(apiResponse.getTheDirectors());

        var response = new AccountsByCustomerIdResponse();
        response.setData(data);
        log.info("BankOne-GetAccountByCustomerIdResponse: {}", response);

        return response;
    }


    public AccountByBVN getAccountsByBvn(String bvn) {
        log.info("BankOne-GetAccountByCustomerIdRequest: {}", bvn);

        String apiUrl = baseUrl + "/api/Account/GetAccountsByBvn/2" + "?authtoken=" + authToken + "&bvn=" + bvn;


        var apiResponse = httpClient.callApi(null, AccountsByCustomerIdResponseDto.class, HttpMethod.GET,
                apiUrl);

        var response = new AccountByBVN();
        BeanUtils.copyProperties(response.getData(), apiResponse);
        log.info("BankOne-GetAccountByCustomerIdResponse: {}", response);

        return response;
    }

    public AddAccountToCustomerResponse addAccountToCustomer(AddAccountToCustomerRequest addAccountToCustomerRequest) {
        log.info("BankOne-AddAccountToCustomerRequest: {}", addAccountToCustomerRequest);

        String apiUrl = baseUrl + "/api/Account/AddAccountToCustomer/2" + "?authtoken=" + authToken;

        var apiResponse = httpClient.callApi(null, AddAccountToCustomerResponseDto.class, HttpMethod.POST,
                apiUrl);

        var response = new AddAccountToCustomerResponse();
        BeanUtils.copyProperties(response.getData(), apiResponse);
        log.info("BankOne-AddAccountToCustomerResponse: {}", response);

        return response;
    }


    public String retrieveAccountNumber(String acctOpeningTrackingRef, String mfbCode, String version) {
        log.info("BankOne-RetrieveAccountNumberRequest: {} {}", acctOpeningTrackingRef, mfbCode);

        String apiUrl = baseUrl + "/api/Account/RetrieveAccountNumber/2" + "?authtoken=" + authToken + "&acctOpeningTrackingRef=" + acctOpeningTrackingRef + "&mfbCode=" + mfbCode;

        var apiResponse = httpClient.callApi(null, String.class, HttpMethod.POST, apiUrl);

        log.info("BankOne-RetrieveAccountNumberResponse: {}", apiResponse);

        return apiResponse;
    }

    public OutstandingBalanceResponse getOutStandingBalance(String accountNumber, String mfbCode) {
        log.info("BankOne-GetOutStandingBalanceRequest: {}", accountNumber);

        String apiUrl = baseUrl + "/api/Account/GetOutStandingBalance/2" + "?authtoken=" + authToken + "&accountNumber=" + accountNumber + "&mfbCode=" + mfbCode;


        var apiResponse = httpClient.callApi(null, OutstandingBalanceResponseDto.class, HttpMethod.GET,
                apiUrl);

        OutstandingBalanceResponse.Data data = new OutstandingBalanceResponse.Data();
        data.setAccountNumber(apiResponse.getAccountNumber());
        data.setAvailableBalance(apiResponse.getAvailableBalance());
        data.setFinancialDate(apiResponse.getFinancialDate());
        data.setMfbCode(apiResponse.getMfbCode());

        var response = new OutstandingBalanceResponse();
        response.setData(data);

        log.info("BankOne-GetOutStandingBalanceResponse: {}", response);

        return response;
    }

    public Object getTransactionsByAccountNumber(String accountNumber, LocalDateTime fromDate, LocalDateTime toDate, String version) {
        log.info("BankOne-GetTransactionsByAccountNumberRequest: {}", accountNumber);

        String apiUrl = baseUrl + "/api/Account/GetTransactions/2" + "?authtoken=" + authToken + "&accountNumber=" + accountNumber + "&fromDate=" + fromDate + "&toDate=" + toDate;

        var apiResponse = httpClient.callApi(null, Object.class, HttpMethod.GET, apiUrl);

        log.info("BankOne-GetTransactionsByAccountNumberResponse: {}", apiResponse);

        return apiResponse;
    }

    public Object getAccountStatement(String accountNumber, LocalDateTime fromDate, LocalDateTime toDate, boolean isPdf, String version) {
        log.info("BankOne-GetAccountStatementRequest: {}", accountNumber);

        String apiUrl = baseUrl + "/api/Account/GenerateAccountStatement/2" + "?authtoken=" + authToken + "&accountNumber=" + accountNumber + "&fromDate=" + fromDate + "&toDate=" + toDate + "&isPdf=" + isPdf;

        var apiResponse = httpClient.callApi(null, Object.class, HttpMethod.GET, apiUrl);

        log.info("BankOne-GetAccountStatementResponse: {}", apiResponse);

        return apiResponse;
    }

    public Object createSavingsAccount(CreateSavingsAccountRequest createSavingsAccountRequest) {
        log.info("BankOne-CreateSavingsAccountRequest: {}", createSavingsAccountRequest);

        String apiUrl = baseUrl + "/api/Account/CreateSavingsAccount/2" + "?authtoken=" + authToken;

        var apiResponse = httpClient.callApi(null, Object.class, HttpMethod.POST, apiUrl);

        log.info("BankOne-CreateSavingsAccountResponse: {}", apiResponse);

        return apiResponse;
    }

    public AccountEnquiryResponse doAccountEnquiry(AccountEnquiryRequest request) {
        log.info("BankOne-AccountEnquiryRequest: {}", request);

        String apiUrl = thirdPartyBaseUrl + "/Account/AccountEnquiry";

        var apiResponse = httpClient.callApi(request, AccountEnquiryResponse.class, HttpMethod.POST,
                apiUrl);

        log.info("BankOne-AccountEnquiryRequest: {}", apiResponse);

        return apiResponse;
    }


}
