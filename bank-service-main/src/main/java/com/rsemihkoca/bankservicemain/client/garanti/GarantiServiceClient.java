package com.rsemihkoca.bankservicemain.client.garanti;

import com.rsemihkoca.bankservicemain.client.BankServiceClient;
import com.rsemihkoca.bankservicemain.dto.response.GenericResponse;
import com.rsemihkoca.bankservicemain.dto.response.LoanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient(value = "garanti-service")
public interface GarantiServiceClient extends BankServiceClient {

    @GetMapping("garanti/v1/api/loan")
    ResponseEntity<GenericResponse<List<LoanResponse>>> getAll();
}