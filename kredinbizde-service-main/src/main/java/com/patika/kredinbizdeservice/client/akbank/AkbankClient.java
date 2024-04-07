package com.patika.kredinbizdeservice.client.akbank;

import com.patika.kredinbizdeservice.client.BankServiceClient;
import com.patika.kredinbizdeservice.client.dto.request.BankApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AkbankClient implements BankServiceClient {

    private final AkbankServiceClient feignClient;

    public AkbankClient() {
        this.feignClient = null;
    }

    @Autowired
    public AkbankClient(AkbankServiceClient feignClient) {
        this.feignClient = feignClient;
    }
    @Override
    public ApplicationResponse createApplication(BankApplicationRequest request) {
        if (feignClient == null) {
            throw new UnsupportedOperationException("Feign client is not initialized");
        }

        BankApplicationRequest applicationRequest = new BankApplicationRequest();
        applicationRequest.setUserId(1L);
        ApplicationResponse application = feignClient.createApplication(request);

        return application;
    }
}

