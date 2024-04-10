package com.rsemihkoca.garantiservice.mapper;


import com.rsemihkoca.garantiservice.dto.request.LoanRequest;
import com.rsemihkoca.garantiservice.entity.Loan;
import org.modelmapper.AbstractConverter;

import java.time.LocalDateTime;

public class LoanRequestToLoanConverter extends AbstractConverter<LoanRequest, Loan> {

    @Override
    protected Loan convert(LoanRequest loanRequest) {
        return Loan.builder()
                .amount(loanRequest.getAmount())
                .type(loanRequest.getType())
                .duration(loanRequest.getDuration())
                .interestRate(loanRequest.getInterestRate())
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
}