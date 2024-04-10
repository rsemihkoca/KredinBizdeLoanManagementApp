package com.rsemihkoca.bankservicemain.service;


import com.rsemihkoca.bankservicemain.dto.response.MergedLoanResponse;
import com.rsemihkoca.bankservicemain.entity.Constants;
import com.rsemihkoca.bankservicemain.entity.Loan;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoanService {

    private final RedisService redisService;
    private final ModelMapper modelMapper;


    public List<MergedLoanResponse> getAll() {
        return redisService.getAll(Constants.LOAN_CACHE);
    }

}

