package com.sahaplus.baascore.controller;

import com.sahaplus.baascore.service.CustomerService;
import com.sahaplus.baascore.util.BVNValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
//@RequestMapping("/customer")
public class CustomerController {

    @Value("${redirect.url}")
    private String redirectUrl;

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

    @RequestMapping(method = RequestMethod.GET, value = "/saha/jp.do")
    public RedirectView validateBvn (HttpServletRequest request) {
        System.out.println("I got here");
        System.out.println(request.getParameter("action"));
        System.out.println(request.getParameter("code"));
        String code = request.getParameter("code");

        new Thread(() -> {
            bvnValidator.doAfterCallback(code);
        }).start();

        String url = redirectUrl + "?code=" + code;

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;


    }




}
