package com.sahaplus.baascore.bills_payment;

import com.google.gson.Gson;
import com.sahaplus.baascore.bankone_apis.enums.ResponseCodes;
import com.sahaplus.baascore.bankone_apis.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class BillsPaymentHttpClient {
    @Value("${bills.payment.hash.key}")
    private String billPaymentHashKey;

    private final Gson gson;

    private final RestTemplate restTemplate;

    public BillsPaymentHttpClient(Gson gson, RestTemplate restTemplate) {
        this.gson = gson;
        this.restTemplate = restTemplate;
    }

    public <Response, Request> Response consume(Request request, Class<Response> typeClass, HttpMethod httpMethod, String apiUrl) {
        String message = "System Malfunction";
        String code = ResponseCodes.B_SERVER_ERROR.getValue();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.set("hashKey", billPaymentHashKey);
        HttpEntity<Request> entity = new HttpEntity<>(request, header);

        try {
            log.info("API URL: {}", apiUrl);
            log.info("REQUEST BODY: {}", request);

            ResponseEntity<String> responseFromApi = restTemplate.exchange(apiUrl, httpMethod, entity, String.class);
            log.info("RESPONSE FROM API: {}", responseFromApi.getBody());

            return gson.fromJson(responseFromApi.getBody(), typeClass);
        } catch (HttpClientErrorException e) {
            log.info("HttpClientErrorException: {}", e.getResponseBodyAsString());
            message = "Validation error";
            code = ResponseCodes.B_VALIDATION_ERROR.getValue();
        } catch (ResourceAccessException e) {
            log.error("ResourceAccessException: ", e);
            message = "Service not reachable";
            code = ResponseCodes.B_SERVER_ERROR.getValue();
        } catch (Exception e) {
            log.error("Exception: ", e);
        }

        throw new AppException(message, code);
    }
}

