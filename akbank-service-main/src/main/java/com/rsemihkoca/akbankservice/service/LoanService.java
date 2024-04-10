package com.rsemihkoca.akbankservice.service;


import com.rsemihkoca.akbankservice.dto.request.LoanRequest;
import com.rsemihkoca.akbankservice.dto.response.LoanResponse;
import com.rsemihkoca.akbankservice.entity.Constants;
import com.rsemihkoca.akbankservice.entity.Loan;
import com.rsemihkoca.akbankservice.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final ModelMapper modelMapper;

    @CacheEvict(value = Constants.LoanTable.TABLE_NAME, allEntries = true)
    public LoanResponse createLoan(LoanRequest request) {
        Loan loan = modelMapper.map(request, Loan.class);
        Loan savedLoan = loanRepository.save(loan);
        return modelMapper.map(savedLoan, LoanResponse.class);
    }

    @Cacheable(value = Constants.LoanTable.TABLE_NAME)
    public List<LoanResponse> getAll() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream()
                .map(loan -> modelMapper.map(loan, LoanResponse.class))
                .collect(Collectors.toList());
    }

    @Cacheable(value = Constants.LoanTable.TABLE_NAME, key = "#loanId")
    public LoanResponse getById(Long loanId) {
        Loan loan = loanRepository.getLoanById(loanId);
        return modelMapper.map(loan, LoanResponse.class);
    }

    @CacheEvict(value = Constants.LoanTable.TABLE_NAME, allEntries = true)
    public LoanResponse deleteById(Long loanId) {
        Loan loan = loanRepository.getLoanById(loanId);
        loanRepository.delete(loan);
        return modelMapper.map(loan, LoanResponse.class);
    }

    @CacheEvict(value = Constants.LoanTable.TABLE_NAME, allEntries = true)
    public LoanResponse updateLoan(Long loanId, LoanRequest request) {
        Loan loan = loanRepository.getLoanById(loanId);
        loan.setAmount(request.getAmount());
        loan.setDuration(request.getDuration());
        loan.setInterestRate(request.getInterestRate());
        loan.setType(request.getType());
        Loan updatedLoan = loanRepository.save(loan);
        return modelMapper.map(updatedLoan, LoanResponse.class);
    }

}

