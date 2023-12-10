package com.sahaplus.baascore.bankone_apis.modules.customer;

import com.sahaplus.baascore.bankone_apis.modules.customer.dto.backbone.CreateCustomerResponseDto;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.backbone.CustomerDetailsResponseDto;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.backbone.UpdateCustomerResponseDto;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.request.CreateCustomerRequest;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.request.UpdateCustomerRequest;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.response.CreateCustomerResponse;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.response.CustomerDetailsResponse;
import com.sahaplus.baascore.bankone_apis.modules.customer.dto.response.UpdateCustomerResponse;
import com.sahaplus.baascore.bankone_apis.util.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BankOneCustomerService {
    @Value("${bankone.baseurl}")
    private String baseUrl;

    @Value("${bankone.token}")
    private String authToken;
    private final HttpClient httpClient;

    public BankOneCustomerService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public CreateCustomerResponse createCustomer(
            CreateCustomerRequest createCustomerRequest
    ) throws InvocationTargetException, IllegalAccessException {
        log.info("CREATE_CUSTOMER_REQUEST: {}", createCustomerRequest);

        String apiUrl = baseUrl + "/api/Customer/CreateCustomer/2" + "?authtoken=" + authToken;

        CreateCustomerResponseDto apiResponse = httpClient.callApi(createCustomerRequest, CreateCustomerResponseDto.class, HttpMethod.POST, apiUrl);
        System.out.println(apiResponse.toString());

        CreateCustomerResponse.Data data = new CreateCustomerResponse.Data();
        data.setLastName(apiResponse.getLastName());
        data.setOtherNames(apiResponse.getOtherNames());
        data.setAddress(apiResponse.getAddress());
        data.setGender(apiResponse.getGender());
        data.setDateOfBirth(apiResponse.getDateOfBirth());
        data.setPhoneNo(apiResponse.getPhoneNo());
        data.setNationalIdentityNo(apiResponse.getNationalIdentityNo());
        data.setCustomerType(apiResponse.getCustomerType());
        data.setBankVerificationNumber(apiResponse.getBankVerificationNumber());
        data.setCustomerID(apiResponse.getCustomerID());
        data.setEmail(apiResponse.getEmail());


        var response = CreateCustomerResponse.builder()
                .data(data)
                .build();
        log.info("CREATE_CUSTOMER_RESPONSE: {}", response);

        return response;
    }

    public UpdateCustomerResponse updateCustomer(
            UpdateCustomerRequest updateCustomerRequest
    ) throws InvocationTargetException, IllegalAccessException {
        log.info("UPDATE_CUSTOMER_REQUEST: {}", updateCustomerRequest);

        String apiUrl = baseUrl + "/api/Customer/UpdateCustomer/2" + "?authtoken=" + authToken;

        var apiResponse = httpClient.callApi(updateCustomerRequest, UpdateCustomerResponseDto.class, HttpMethod.POST,
                apiUrl);

        var response = new UpdateCustomerResponse();
        BeanUtils.copyProperties(response.getData(), apiResponse);
        log.info("UPDATE_CUSTOMER_RESPONSE: {}", response);

        return response;
    }

    public CustomerDetailsResponse getCustomerDetailsByCustomerId(
            String customerId) {
        log.info("GET_CUSTOMER_DETAILS_BY_CUSTOMER_ID_REQUEST: {}", customerId);
        String apiUrl = baseUrl + "/api/Customer/GetByCustomerID/" + "?authtoken=" + authToken + "&customerId=" + customerId;


        var apiResponse = httpClient.callApi(null, CustomerDetailsResponseDto.class, HttpMethod.GET,
                apiUrl);

        var response = new CustomerDetailsResponse();
        BeanUtils.copyProperties(response.getData(), apiResponse);
        log.info("GET_CUSTOMER_DETAILS_BY_CUSTOMER_ID_RESPONSE: {}", apiResponse);

        return response;
    }

    public CustomerDetailsResponse getCustomerDetailsByCustomerPhoneNumber(
            String customerPhoneNumber,
            String version) {
        log.info("GET_CUSTOMER_DETAILS_BY_CUSTOMER_PHONE_NUMBER_REQUEST: {}", customerPhoneNumber);

        String apiUrl = baseUrl + "/api/Customer/GetByCustomerPhoneNumber/2" + "?authtoken=" + authToken + "&PhoneNumber=" + customerPhoneNumber;


        var apiResponse = httpClient.callApi(null, CustomerDetailsResponseDto.class, HttpMethod.GET,
                apiUrl);

        var response = new CustomerDetailsResponse();
        BeanUtils.copyProperties(response.getData(), apiResponse);
        log.info("GET_CUSTOMER_DETAILS_BY_CUSTOMER_PHONE_NUMBER_RESPONSE: {}", apiResponse);

        return response;
    }

    public CustomerDetailsResponse getCustomerDetailsByCustomerBVN(
            String customerBVN,
            String version) {
        log.info("GET_CUSTOMER_DETAILS_BY_CUSTOMER_BVN_REQUEST: {}", customerBVN);

        String apiUrl = baseUrl + "/api/Customer/GetByCustomerPhoneNumber/2" + "?authtoken=" + authToken + "&bvn=" + customerBVN;


        var apiResponse = httpClient.callApi(null, CustomerDetailsResponseDto.class, HttpMethod.GET,
                apiUrl);

        var response = new CustomerDetailsResponse();
        BeanUtils.copyProperties(response.getData(), apiResponse);
        log.info("GET_CUSTOMER_DETAILS_BY_CUSTOMER_BVN_RESPONSE: {}", apiResponse);

        return response;
    }

    public CustomerDetailsResponse getCustomerDetailsByCustomerAccountNumber(
            String customerAccountNumber) {
        log.info("GET_CUSTOMER_DETAILS_BY_CUSTOMER_ACCOUNT_NUMBER_REQUEST: {}", customerAccountNumber);

        String apiUrl = baseUrl + "/api/Customer/GetByAccountNumber/2" + "?authtoken=" + authToken + "&accountNumber=" + customerAccountNumber;


        var apiResponse = httpClient.callApi(null, CustomerDetailsResponseDto.class, HttpMethod.GET, apiUrl);
        System.out.println(apiResponse.toString());

        List<CustomerDetailsResponse.Account> accounts = new ArrayList<>();

        for (CustomerDetailsResponseDto.Account account : apiResponse.getAccounts()) {
            CustomerDetailsResponse.Account individualAccount = new CustomerDetailsResponse.Account();
            individualAccount.setAccessLevel(account.getAccessLevel());
            individualAccount.setAccountBalance(account.getAccountBalance());
            individualAccount.setAccountNumber(account.getAccountNumber());
            individualAccount.setAccountStatus(account.getAccountStatus());
            individualAccount.setAccountType(account.getAccountType());
            individualAccount.setBranch(account.getBranch());
            individualAccount.setCustomerId(account.getCustomerID());
            individualAccount.setCustomerName(account.getCustomerName());
            individualAccount.setDateCreated(account.getDateCreated());
            individualAccount.setLastActivityDate(account.getLastActivityDate());
            individualAccount.setNUBAN(account.getNUBAN());
            individualAccount.setRefree1CustomerID(account.getRefree1CustomerID());
            individualAccount.setRefree2CustomerID(account.getRefree2CustomerID());
            individualAccount.setReferenceNo(account.getReferenceNo());
            accounts.add(individualAccount);
        }

        CustomerDetailsResponse.Data data = new CustomerDetailsResponse.Data();
        data.setId(apiResponse.getId());
        data.setCustomerId(apiResponse.getCustomerID());
        data.setLastName(apiResponse.getLastName());
        data.setGender(apiResponse.getGender());
        data.setGenderString(apiResponse.getGenderString());
        data.setOtherNames(apiResponse.getOtherNames());
        data.setAddress(apiResponse.getAddress());
        data.setEmail(apiResponse.getEmail());
        data.setLandmark(apiResponse.getLandmark());
        data.setPhoneNumber(apiResponse.getPhoneNumber());
        data.setNickName(apiResponse.getNickName());
        data.setBankVerificationNumber(apiResponse.getBankVerificationNumber());
        data.setEmailNotification(apiResponse.isEmailNotification());
        data.setPhoneNotification(apiResponse.isPhoneNotification());
        data.setDistrictOfResidence(apiResponse.getDistrictOfResidence());
        data.setDistrictOfBusiness(apiResponse.getDistrictOfBusiness());
        data.setIdentificationNumber(apiResponse.getIdentificationNumber());
        data.setDateOfBirth(apiResponse.getDateOfBirth());
        data.setHomeTown(apiResponse.getHomeTown());
        data.setLocalGovernment(apiResponse.getLocalGovernment());
        data.setMothersMaidenName(apiResponse.getMothersMaidenName());
        data.setNationale(apiResponse.getNationale());
        data.setTestQuestion(apiResponse.getTestQuestion());
        data.setTestAnswer(apiResponse.getTestAnswer());
        data.setState(apiResponse.getState());
        data.setWorkAddress(apiResponse.getWorkAddress());
        data.setWorkAnnualIncome(apiResponse.getWorkAnnualIncome());
        data.setWorkEmployerName(apiResponse.getWorkEmployerName());
        data.setWorkNextOfKin(apiResponse.getWorkNextOfKin());
        data.setWorkNextOfKinAddress(apiResponse.getWorkNextOfKinAddress());
        data.setWorkNextOfKinPhoneNo(apiResponse.getWorkNextOfKinPhoneNo());
        data.setWorkNextOfKinRelationship(apiResponse.getWorkNextOfKinRelationship());
        data.setWorkOccupation(apiResponse.getWorkOccupation());
        data.setWorkPhoneNo(apiResponse.getWorkPhoneNo());
        data.setNameOnUtilityBill(apiResponse.getNameOnUtilityBill());
        data.setAddressOnUtilityBill(apiResponse.getAddressOnUtilityBill());
        data.setDateOnUtilityBill(apiResponse.getDateOnUtilityBill());
        data.setCommentsForUtilityBill(apiResponse.getCommentsForUtilityBill());
        data.setUtilityBillInfoComplete(apiResponse.isIsUtilityBillInfoComplete());
        data.setNextOfKinEmailAddress(apiResponse.getNextOfKinEmailAddress());
        data.setCustomerPhoto(apiResponse.getCustomerPhoto());
        data.setCustomerSignature(apiResponse.getCustomerSignature());
        data.setAccounts(accounts);

        var response = new CustomerDetailsResponse();
        response.setData(data);
        log.info("GET_CUSTOMER_DETAILS_BY_CUSTOMER_ACCOUNT_NUMBER_RESPONSE: {}", apiResponse);

        return response;
    }

    public boolean isCustomerExistByEmail(
            String customerEmail,
            String version) {
        log.info("IS_CUSTOMER_EXIST_BY_EMAIL_REQUEST: {}", customerEmail);

        String apiUrl = baseUrl + "/api/Customer/IsCustomerExistByEmail/2" + "?authtoken=" + authToken +  "&email=" + customerEmail;

        var apiResponse = httpClient.callApi(null, Boolean.class, HttpMethod.GET,
                apiUrl);

        log.info("IS_CUSTOMER_EXIST_BY_EMAIL_RESPONSE: {}", apiResponse);

        return apiResponse;
    }

    public boolean isCustomerExistByPhoneNo(
            String customerPhoneNo,
            String version) {
        log.info("IS_CUSTOMER_EXIST_BY_PHONE_NO_REQUEST: {}", customerPhoneNo);

        String apiUrl = baseUrl + "/api/Customer/IsCustomerExistByPhoneNo/2" + "?authtoken=" + authToken +  "&phoneNumber=" + customerPhoneNo;

        var apiResponse = httpClient.callApi(null, Boolean.class, HttpMethod.GET,
                apiUrl);

        log.info("IS_CUSTOMER_EXIST_BY_PHONE_NO_RESPONSE: {}", apiResponse);

        return apiResponse;
    }
}
