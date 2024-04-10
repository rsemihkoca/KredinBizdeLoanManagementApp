package com.rsemihkoca.garantiservice.service;

import com.rsemihkoca.garantiservice.dto.request.LoanRequest;
import com.rsemihkoca.garantiservice.entity.Loan;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.rsemihkoca.garantiservice.enums.LoanType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Slf4j
@AllArgsConstructor
public class DataInitializerService implements CommandLineRunner {

    private final LoanService loanService;
    private final ModelMapper modelMapper;

    @Override
    public void run(String... args) {
        log.info("DataInitializerService is running...");

        LoanRequest loanRequest = LoanRequest.builder()
                .amount(100_000.0)
                .type(LoanType.ARAC_KREDISI)
                .duration(12)
                .interestRate(1.5)
                .build();
        loanService.createLoan(loanRequest);

        loanRequest = LoanRequest.builder()
                .amount(200_000.0)
                .type(LoanType.KONUT_KREDISI)
                .duration(24)
                .interestRate(1.2)
                .build();
        loanService.createLoan(loanRequest);

        loanRequest = LoanRequest.builder()
                .amount(300_000.0)
                .type(LoanType.IHTIYAC_KREDISI)
                .duration(36)
                .interestRate(1.3)
                .build();
        loanService.createLoan(loanRequest);

        loanRequest = LoanRequest.builder()
                .amount(400_000.0)
                .type(LoanType.TATIL_KREDISI)
                .duration(48)
                .interestRate(1.4)
                .build();
        loanService.createLoan(loanRequest);

        loanRequest = LoanRequest.builder()
                .amount(500_000.0)
                .type(LoanType.ESNAF_KREDISI)
                .duration(60)
                .interestRate(1.6)
                .build();
        loanService.createLoan(loanRequest);

        loanRequest = LoanRequest.builder()
                .amount(600_000.0)
                .type(LoanType.OGRENCI_KREDISI)
                .duration(72)
                .interestRate(1.7)
                .build();

        loanService.createLoan(loanRequest);

        loanRequest = LoanRequest.builder()
                .amount(700_000.0)
                .type(LoanType.SAGLIK_KREDISI)
                .duration(84)
                .interestRate(1.8)
                .build();


    }
}
