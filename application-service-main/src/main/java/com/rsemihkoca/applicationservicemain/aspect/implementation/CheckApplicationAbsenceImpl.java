package com.rsemihkoca.applicationservicemain.aspect.implementation;

import com.rsemihkoca.applicationservicemain.model.ApplicationPipeline;
import com.rsemihkoca.applicationservicemain.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
@AllArgsConstructor
@Order(value = 3)
public class CheckApplicationAbsenceImpl {

    private final ApplicationPipeline applicationPipeline;
    private final ApplicationService applicationService;

    @Before("@annotation(com.rsemihkoca.applicationservicemain.aspect.signature.CheckApplicationAbsence) && execution(* *(..))")
    public void checkApplicationAbsence(JoinPoint joinPoint) {
        Long loanId = applicationPipeline.getCurrentLoanRequest().getLoanId();
        String userEmail = applicationPipeline.getCurrentUserEmail();

        if (userEmail == null || loanId == null) {
            throw new RuntimeException("User email or loan id is null");
        }
        getExistingApplication(userEmail, loanId);

    }

    private void getExistingApplication(String userEmail, Long loanId) {
        Object existingApplication = applicationService.getApplicationByUserEmailAndLoanId(userEmail, loanId);
        if (existingApplication != null) {
            throw new RuntimeException("There is already an active application for this loan");
        }
    }
}

