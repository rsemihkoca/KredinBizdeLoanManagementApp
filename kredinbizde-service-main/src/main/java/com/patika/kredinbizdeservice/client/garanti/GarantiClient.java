package com.patika.kredinbizdeservice.client.garanti;

import com.patika.kredinbizdeservice.client.BankServiceClient;
import com.patika.kredinbizdeservice.client.dto.request.BankApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GarantiClient implements BankServiceClient {

    private final GarantiServiceClient feignClient;

    public GarantiClient() {
        this.feignClient = null;
    }

    @Autowired
    public GarantiClient(GarantiServiceClient feignClient) {
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

