package com.sahaplus.baascore.controller;

import com.sahaplus.baascore.service.CustomerService;
import com.sahaplus.baascore.util.BVNValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final BVNValidator bvnValidator;

    private final CustomerService customerService;

    public CustomerController(BVNValidator bvnValidator, CustomerService customerService) {
        this.bvnValidator = bvnValidator;
        this.customerService = customerService;
    }

    @GetMapping("/getCustomerDetailsByCustomerId")
    public void getCustomerDetailsByCustomerId(@RequestParam("customerId") String customerId) {
        customerService.getCustomerDetailsByCustomerId(customerId);
    }

    @GetMapping("/authorizeBvnValidation")
    public ResponseEntity<String> authorize() {
        return bvnValidator.authorize();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sahaplus")
    public ResponseEntity<String> validateBvn ( @RequestParam (name = "action") String action, @RequestParam(name = "code") String code) {
       return bvnValidator.doAfterCallback(code);
    }




}
