package com.rsemihkoca.applicationservicemain.client.akbank;

import com.rsemihkoca.applicationservicemain.client.BankServiceClient;
import com.rsemihkoca.applicationservicemain.dto.request.BankApplicationRequest;
import com.rsemihkoca.applicationservicemain.dto.response.ApplicationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "akbank-service", url = "localhost:5556")
public interface AkbankServiceClient extends BankServiceClient {

    @PostMapping("api/akbank/v1/application")
    ApplicationResponse createApplication(@RequestBody BankApplicationRequest request);
}
