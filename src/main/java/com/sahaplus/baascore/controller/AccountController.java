package com.sahaplus.baascore.controller;

import com.sahaplus.baascore.auth.Permission;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.AccountByAccountNumberResponse;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.AccountsByCustomerIdResponse;
import com.sahaplus.baascore.bankone_apis.modules.account.dto.response.OutstandingBalanceResponse;
import com.sahaplus.baascore.dto.request.AccountCreationRequest;
import com.sahaplus.baascore.dto.request.OnboardExistingCustomerRequest;
import com.sahaplus.baascore.dto.request.OnboardExistingCustomerResponse;
import com.sahaplus.baascore.dto.response.AccountCreationResponse;
import com.sahaplus.baascore.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Permission("CREATE_NEW_CUSTOMER")
    @Operation(summary = "Create Customer And Wallet For New Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create Customer And Wallet For New Customer",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountCreationResponse.class))})})
    @PostMapping("/createWalletForNewCustomer")
    public AccountCreationResponse createAccountForNewCustomer(@RequestBody @Validated AccountCreationRequest request) throws InvocationTargetException, IllegalAccessException {
        return accountService.accountOpeningForNewCustomersIndividual(request);
    }

    @Operation(summary = "Onboard Existing Customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Onboard Existing Customers",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OnboardExistingCustomerResponse.class))})})
    @GetMapping("/onboardExistingCustomers")
    public OnboardExistingCustomerResponse onboardExistingCustomers(@RequestBody OnboardExistingCustomerRequest onboardExistingCustomerRequest) {
        return accountService.onBoardingExistingCustomers(onboardExistingCustomerRequest);
    }

    @Operation(summary = "Get Account By Account Number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Account By Account Number",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountByAccountNumberResponse.class))})})
    @GetMapping("/getAccountByAccountNumber")
    public AccountByAccountNumberResponse getAccountByAccountNumber(@RequestParam("accountNumber") String accountNumber) {
        return accountService.getAccountByAccountNumber(accountNumber);
    }

    @Operation(summary = "Get Outstanding Balance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Outstanding Balance",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OutstandingBalanceResponse.class))})})
    @GetMapping("/getOutstandingBalance")
    public OutstandingBalanceResponse getOutstandingBalance(@RequestParam("accountNumber") String accountNumber, @RequestParam("mfbCode") String mfbCode) {
        return accountService.getOutstandingBalance(accountNumber, mfbCode);
    }

    @Operation(summary = "Get Accounts By Customer Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Accounts By Customer Id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountsByCustomerIdResponse.class))})})
    @GetMapping("/getAccountsByCustomerId")
    public AccountsByCustomerIdResponse getAccountsByCustomerId(@RequestParam("customerId") String customerId) {
        return accountService.getAccountsByCustomerId(customerId);
    }
}
