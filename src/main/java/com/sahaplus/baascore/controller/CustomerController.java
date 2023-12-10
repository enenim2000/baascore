package com.sahaplus.baascore.controller;

import com.sahaplus.baascore.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getCustomerDetailsByCustomerId")
    public void getCustomerDetailsByCustomerId(@RequestParam("customerId") String customerId) {
        customerService.getCustomerDetailsByCustomerId(customerId);
    }
}
