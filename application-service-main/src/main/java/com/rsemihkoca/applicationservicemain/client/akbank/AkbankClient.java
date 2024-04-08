package com.rsemihkoca.applicationservicemain.client.akbank;

import com.rsemihkoca.applicationservicemain.client.BankServiceClient;
import com.rsemihkoca.applicationservicemain.dto.request.BankApplicationRequest;
import com.rsemihkoca.applicationservicemain.dto.response.ApplicationResponse;
import org.springframework.stereotype.Component;

@Component
public class AkbankClient implements BankServiceClient {

    private final AkbankServiceClient feignClient;

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

