package com.sahaplus.baascore.bills_payment;

import com.sahaplus.baascore.bills_payment.dto.request.AirtimeVendingRequest;
import com.sahaplus.baascore.bills_payment.dto.request.DataBundleLookUpRequest;
import com.sahaplus.baascore.bills_payment.dto.response.AirtimeVendingResponse;
import com.sahaplus.baascore.bills_payment.dto.response.DataBundleLookUpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpMethod.POST;


@Service
@Slf4j
public class BillPaymentService {
    private final BillsPaymentHttpClient httpClient;
    @Value("${bills.payment.url}")
    private String url;

    public BillPaymentService(BillsPaymentHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    public AirtimeVendingResponse vendAirtime(AirtimeVendingRequest request) {
        log.info("Airtime Vending Request {}", request);

        AirtimeVendingResponse response = httpClient.consume(request, AirtimeVendingResponse.class, POST, url);
        log.info("Airtime Vending Response {}", response );

        return response;
    }

    public DataBundleLookUpResponse dataBundleLookup (DataBundleLookUpRequest request) {
        log.info("Data Bundle Look Up Request {}", request);

        DataBundleLookUpResponse response = httpClient.consume(request, DataBundleLookUpResponse.class, POST, url);
        log.info("Data Bundle Look Up Response {}", response);

        return  response;
    }

//    public DataVendingResponse vendData(VendData)
}
