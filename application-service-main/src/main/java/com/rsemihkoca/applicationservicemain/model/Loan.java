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

    @Column(name = loanTable.AMOUNT, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = loanTable.LOAN_TYPE)
    private LoanType loanType;

    @OneToOne()
    @JoinColumn(name = applicationTable.APPLICATION_ID, unique = true)
    private Application application;

    @OneToOne()
    @JoinColumn(name = bankTable.BANK_ID, unique = true)
    private Bank bank;

}


