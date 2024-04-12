package com.rsemihkoca.applicationservicemain.aspect.implementation;

import com.rsemihkoca.applicationservicemain.client.bankservice.BankServiceClient;
import com.rsemihkoca.applicationservicemain.dto.request.interfaces.hasBankName;
import com.rsemihkoca.applicationservicemain.dto.request.interfaces.hasLoanId;
import com.rsemihkoca.applicationservicemain.dto.response.GenericResponse;
import com.rsemihkoca.applicationservicemain.dto.response.MergedLoanResponse;
import com.rsemihkoca.applicationservicemain.model.ApplicationPipeline;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
@Order(value = 2)
public class CheckLoanExistence {

    private final BankServiceClient bankServiceClient;
    private final ApplicationPipeline applicationPipeline;

    @Before("@annotation(com.rsemihkoca.applicationservicemain.aspect.signature.CheckLoanExistence) && execution(* *(..))")
    public void checkLoanExistence(JoinPoint joinPoint) {
        // get any bank name and Long loanId from the request
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof hasLoanId && arg instanceof hasBankName) {
                var request = (hasLoanId & hasBankName) arg;
                String bankName = request.getBankName();
                Long loanId = request.getLoanId();
                findLoanByLoanIdAndBankName(loanId, bankName);
            }
        }

    }

    private void findLoanByLoanIdAndBankName(Long loanId, String bankName) {
        GenericResponse<List<MergedLoanResponse>> loanResponse = bankServiceClient.getAll().getBody();
        if (loanResponse == null) {
            throw new RuntimeException("BankService returned empty response");
        }
        String bankNameUpperCase = bankName.toUpperCase();
        List<MergedLoanResponse> data = loanResponse.getData();
        MergedLoanResponse mergedLoanResponse = data.stream()
                .filter(loan -> loan.getLoanId().equals(loanId) && loan.getBankName().toUpperCase().equals(bankNameUpperCase))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        applicationPipeline.setCurrentLoanRequest(mergedLoanResponse);
    }
}

