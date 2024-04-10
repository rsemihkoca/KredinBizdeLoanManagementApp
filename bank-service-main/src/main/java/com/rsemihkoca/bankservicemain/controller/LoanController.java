package com.rsemihkoca.bankservicemain.controller;

import com.rsemihkoca.bankservicemain.dto.response.GenericResponse;
import com.rsemihkoca.bankservicemain.dto.response.MergedLoanResponse;
import com.rsemihkoca.bankservicemain.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("loan")
    public ResponseEntity<GenericResponse<List<MergedLoanResponse>>> getAll() {
        return ResponseEntity.ok(GenericResponse.success(loanService.getAll()));
    }
}
