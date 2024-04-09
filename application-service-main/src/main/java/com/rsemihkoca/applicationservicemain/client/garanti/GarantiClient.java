package com.rsemihkoca.applicationservicemain.client.garanti;

import com.rsemihkoca.applicationservicemain.client.BankServiceClient;
import com.rsemihkoca.applicationservicemain.dto.request.BankApplicationRequest;
import com.rsemihkoca.applicationservicemain.dto.response.ApplicationResponse;
import org.springframework.stereotype.Component;

@Component
public class GarantiClient implements BankServiceClient {

    private final GarantiServiceClient feignClient;

    public GarantiClient() {
        this.feignClient = null;
    }

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

