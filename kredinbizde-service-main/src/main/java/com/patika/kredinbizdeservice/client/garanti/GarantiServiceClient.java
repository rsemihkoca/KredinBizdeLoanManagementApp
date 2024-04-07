package com.patika.kredinbizdeservice.client.garanti;

import com.patika.kredinbizdeservice.client.BankServiceClient;
import com.patika.kredinbizdeservice.client.dto.request.BankApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "garanti-service", url = "localhost:5555")
public interface GarantiServiceClient extends BankServiceClient {

    @PostMapping("api/garanti/v1/applications")
    ApplicationResponse createApplication(@RequestBody BankApplicationRequest request);
}
