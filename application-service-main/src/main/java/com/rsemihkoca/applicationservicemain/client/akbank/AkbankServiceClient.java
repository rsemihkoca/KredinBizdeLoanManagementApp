package com.rsemihkoca.applicationservicemain.client.akbank;

import com.patika.kredinbizdeservice.client.BankServiceClient;
import com.patika.kredinbizdeservice.client.dto.request.BankApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "akbank-service", url = "localhost:5556")
public interface AkbankServiceClient extends BankServiceClient {

    @PostMapping("api/akbank/v1/application")
    ApplicationResponse createApplication(@RequestBody BankApplicationRequest request);
}
