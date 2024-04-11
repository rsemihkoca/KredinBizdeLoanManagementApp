package com.rsemihkoca.bankservicemain.client.akbank;

import com.rsemihkoca.bankservicemain.dto.response.GenericResponse;
import com.rsemihkoca.bankservicemain.dto.response.LoanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.rsemihkoca.bankservicemain.client.BankServiceClient;
import java.util.List;

@FeignClient(value = "akbank-service-main")
public interface AkbankServiceClient extends BankServiceClient {

    @GetMapping("akbank/v1/api/loan")
    ResponseEntity<GenericResponse<List<LoanResponse>>> getAll();
}