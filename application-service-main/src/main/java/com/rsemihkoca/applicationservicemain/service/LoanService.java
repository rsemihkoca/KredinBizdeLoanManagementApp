package com.rsemihkoca.applicationservicemain.service;

import com.rsemihkoca.applicationservicemain.model.Loan;
import com.rsemihkoca.applicationservicemain.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService {

    private final LoanRepository LoanRepository;

    public void createLoan(Loan Loan) {
        Loan savedLoan = LoanRepository.save(Loan);
        log.info("Loan saved successfully");
    }

    public Loan getLoanById(Long id) {
        return LoanRepository.findById(id).orElse(null);
    }

    public List<Loan> findAll() {
        return LoanRepository.findAll();
    }

    public void deleteAll() {
        LoanRepository.deleteAll();
    }
}
