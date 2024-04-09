package com.rsemihkoca.applicationservicemain.model;

import com.rsemihkoca.applicationservicemain.enums.LoanType;
import jakarta.persistence.*;
import lombok.Data;
import com.rsemihkoca.applicationservicemain.model.Constants.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = loanTable.TABLE_NAME)
public class Loan implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = loanTable.LOAN_ID)
    private Long loanId;

    @Column(name = loanTable.AMOUNT,unique = false, nullable = false)
    private BigDecimal amount;

    @Column(name = loanTable.DURATION, unique = false, nullable = false)
    private Integer duration;

    @Column(name = loanTable.INTEREST_RATE, unique = false, nullable = false)
    private BigDecimal interestRate;

    @Enumerated(EnumType.STRING)
    @Column(name = loanTable.LOAN_TYPE, unique = false, nullable = false)
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name=bankTable.BANK_ID, nullable=false)
    private Bank bank;

}


