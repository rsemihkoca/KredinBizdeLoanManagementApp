package com.patika.kredinbizdeservice.model;

import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.enums.VechileStatuType;

import java.math.BigDecimal;

public class VechileLoan extends Loan {

    private LoanType loanType = LoanType.ARAC_KREDISI;
    private VechileStatuType vechileStatuType;

    public VechileLoan() {

    }

    public VechileLoan(BigDecimal amount, Integer installment, Double interestRate) {
        super(amount, installment, interestRate);
    }

    public LoanType getLoanType() {
        return loanType;
    }

    @Override
    void calculate(BigDecimal amount, int installment) {
        //istediği kadar ödeme yapabilir.
    }
}
