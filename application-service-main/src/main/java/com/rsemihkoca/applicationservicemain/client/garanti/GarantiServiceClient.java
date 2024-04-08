package com.rsemihkoca.applicationservicemain.client.garanti;

import com.rsemihkoca.applicationservicemain.client.BankServiceClient;
import com.rsemihkoca.applicationservicemain.dto.request.BankApplicationRequest;
import com.rsemihkoca.applicationservicemain.dto.response.ApplicationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "garanti-service", url = "localhost:5555")
public interface GarantiServiceClient extends BankServiceClient {

    @PostMapping("api/garanti/v1/application")
    ApplicationResponse createApplication(@RequestBody BankApplicationRequest request);
}
