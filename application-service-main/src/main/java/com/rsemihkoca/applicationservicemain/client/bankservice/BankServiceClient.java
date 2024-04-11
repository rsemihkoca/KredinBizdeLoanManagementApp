package com.rsemihkoca.applicationservicemain.client.bankservice;

import com.rsemihkoca.applicationservicemain.dto.response.GenericResponse;
import com.rsemihkoca.applicationservicemain.dto.response.MergedLoanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient(name = "bank-service-main")
public interface BankServiceClient {

    @GetMapping("/api/v1/loan")
    ResponseEntity<GenericResponse<List<MergedLoanResponse>>> getAll();
}
