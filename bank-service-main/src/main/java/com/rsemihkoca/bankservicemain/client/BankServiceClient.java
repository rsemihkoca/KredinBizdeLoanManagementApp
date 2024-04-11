package com.rsemihkoca.bankservicemain.client;


import com.rsemihkoca.bankservicemain.dto.response.GenericResponse;
import com.rsemihkoca.bankservicemain.dto.response.LoanResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface BankServiceClient {
    ResponseEntity<GenericResponse<List<LoanResponse>>> getAll();
}
