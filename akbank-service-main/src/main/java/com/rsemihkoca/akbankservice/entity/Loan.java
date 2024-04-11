package com.rsemihkoca.akbankservice.entity;


import com.rsemihkoca.akbankservice.enums.LoanType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import  com.rsemihkoca.akbankservice.entity.Constants.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;


@Entity
@Data
@Table(name = LoanTable.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = LoanTable.ID)
    private Long id;

    @Column(name = LoanTable.CREATE_DATE)
    private LocalDateTime createDate;

    @Column(name = LoanTable.UPDATE_DATE)
    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    @Column(name = LoanTable.TYPE, length = 50)
    private LoanType type;

    @Column(name = LoanTable.AMOUNT)
    private Double amount;

    @Column(name = LoanTable.INTEREST_RATE)
    private Double interestRate;

    @Column(name = LoanTable.DURATION)
    private Integer duration;

}