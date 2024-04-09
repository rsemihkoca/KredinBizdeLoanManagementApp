package com.rsemihkoca.applicationservicemain.service;

import com.rsemihkoca.applicationservicemain.model.Bank;
import com.rsemihkoca.applicationservicemain.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService {

    private final BankRepository bankRepository;

    public Bank createBank(Bank bank) {
        Bank savedBank = bankRepository.save(bank);
        log.info("Bank saved successfully");
        return savedBank;
    }

    public List<Bank> findAll() {
        return bankRepository.findAll();
    }
}
