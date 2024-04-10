package com.rsemihkoca.akbankservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("akbank/v1/api/")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("loan")
    public ResponseEntity<LoanResponse> createLoan(@RequestBody LoanRequest request) {
        return ResponseEntity.ok(loanService.createLoan(request));
    }

    @GetMapping("loan")
    public ResponseEntity<List<LoanResponse>> getAll() {
        return ResponseEntity.ok(loanService.getAll());
    }

    @GetMapping("loan/{loanId}")
    public ResponseEntity<LoanResponse> getById(@PathVariable("loanId") Long loanId) {
        return ResponseEntity.ok(loanService.getById(loanId));
    }

    @DeleteMapping("loan/{loanId}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable("loanId") Long loanId) {
        return ResponseEntity.ok(loanService.deleteById(loanId));
    }

    @PutMapping("loan/{loanId}")
    public ResponseEntity<LoanResponse> updateLoan(@PathVariable("loanId") Long loanId, @RequestBody LoanRequest request) {
        return ResponseEntity.ok(loanService.updateLoan(loanId, request));
    }


}
