package com.rsemihkoca.garantiservice.controller;

import com.rsemihkoca.garantiservice.dto.response.GenericResponse;
import com.rsemihkoca.garantiservice.dto.response.LoanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rsemihkoca.garantiservice.dto.request.LoanRequest;
import com.rsemihkoca.garantiservice.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("garanti/v1/api/")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("loan")
    public ResponseEntity<GenericResponse<LoanResponse>> createLoan(@RequestBody LoanRequest request) {
        return ResponseEntity.ok(GenericResponse.success(loanService.createLoan(request)));
    }

    @GetMapping("loan")
    public ResponseEntity<GenericResponse<List<LoanResponse>>> getAll() {
        return ResponseEntity.ok(GenericResponse.success(loanService.getAll()));
    }

    @GetMapping("loan/{loanId}")
    public ResponseEntity<GenericResponse<LoanResponse>> getById(@PathVariable("loanId") Long loanId) {
        return ResponseEntity.ok(GenericResponse.success(loanService.getById(loanId)));
    }

    @DeleteMapping("loan/{loanId}")
    public ResponseEntity<GenericResponse<LoanResponse>> deleteById(@PathVariable("loanId") Long loanId) {
        return ResponseEntity.ok(GenericResponse.success(loanService.deleteById(loanId)));
    }

    @PutMapping("loan/{loanId}")
    public ResponseEntity<GenericResponse<LoanResponse>> updateLoan(@PathVariable("loanId") Long loanId, @RequestBody LoanRequest request) {
        return ResponseEntity.ok(GenericResponse.success(loanService.updateLoan(loanId, request)));
    }

    @PostMapping("errorTest")
    public ResponseEntity<GenericResponse<LoanResponse>> testKafka() {
        throw new RuntimeException("test exception");
    }
}
