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
    private Long loanId;

    @OneToOne(mappedBy = loanTable.TABLE_NAME)
    private Application application;

    private BigDecimal amount;
    private Integer installment;
    private Bank bank;
    private Double interestRate;
    private LoanType loanType;
}


