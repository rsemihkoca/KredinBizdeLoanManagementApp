package com.rsemihkoca.applicationservicemain.service.datainitializer;

import com.rsemihkoca.applicationservicemain.model.*;
import com.rsemihkoca.applicationservicemain.service.BankService;
import com.rsemihkoca.applicationservicemain.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import com.rsemihkoca.applicationservicemain.enums.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final LoanService loanService;
    private final BankService bankService;

    public DataInitializer(LoanService loanService, BankService bankService) {
        this.loanService = loanService;
        this.bankService = bankService;
    }

    @Override
    public void run(String... args) {

        // delete all data before initializing
        loanService.deleteAll();
        bankService.deleteAll();
        CompletableFuture<Boolean> bankCreationFuture = CompletableFuture.supplyAsync(() -> {
            Bank bank1 = new Bank();
            bank1.setBankId(1L);
            bank1.setName(BankType.AKBANK.name());
            bankService.createBank(bank1);

            Bank bank2 = new Bank();
            bank2.setBankId(2L);
            bank2.setName(BankType.GARANTI.name());
            bankService.createBank(bank2);
            return true;
        });

        bankCreationFuture.thenRun(() -> {

            Bank bank1 = bankService.getBankById(1L);
            Bank bank2 = bankService.getBankById(2L);

            Loan loan1 = new Loan();
            loan1.setLoanId(1L);
            loan1.setAmount(BigDecimal.valueOf(100_000));
            loan1.setBank(bank1);
            loan1.setLoanType(LoanType.ARAC_KREDISI);
            loan1.setDuration(12);
            loan1.setInterestRate(BigDecimal.valueOf(1.5));
            loanService.createLoan(loan1);

            Loan loan2 = new Loan();
            loan2.setLoanId(2L);
            loan2.setAmount(BigDecimal.valueOf(200_000));
            loan2.setBank(bank1);
            loan2.setLoanType(LoanType.KONUT_KREDISI);
            loan2.setDuration(24);
            loan2.setInterestRate(BigDecimal.valueOf(1.2));
            loanService.createLoan(loan2);

            Loan loan3 = new Loan();
            loan3.setLoanId(3L);
            loan3.setAmount(BigDecimal.valueOf(300_000));
            loan3.setBank(bank1);
            loan3.setLoanType(LoanType.IHTIYAC_KREDISI);
            loan3.setDuration(36);
            loan3.setInterestRate(BigDecimal.valueOf(1.0));
            loanService.createLoan(loan3);

            Loan loan4 = new Loan();
            loan4.setLoanId(4L);
            loan4.setAmount(BigDecimal.valueOf(400_000));
            loan4.setBank(bank2);
            loan4.setLoanType(LoanType.ARAC_KREDISI);
            loan4.setDuration(48);
            loan4.setInterestRate(BigDecimal.valueOf(0.9));
            loanService.createLoan(loan4);

            Loan loan5 = new Loan();
            loan5.setLoanId(5L);
            loan5.setAmount(BigDecimal.valueOf(500_000));
            loan5.setBank(bank2);
            loan5.setLoanType(LoanType.ARAC_KREDISI);
            loan5.setDuration(60);
            loan5.setInterestRate(BigDecimal.valueOf(0.8));
            loanService.createLoan(loan5);


            log.info("Loan and Bank data initialized");
        });
        bankCreationFuture.join(); // veya bankCreationFuture.get();

        log.info("Loan and Bank data initialized");

    }
}
