package com.rsemihkoca.akbankservice.dto.request;

import com.rsemihkoca.akbankservice.enums.LoanType;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;



@Data
@ToString
@Builder
@AllArgsConstructor
public class LoanRequest implements Serializable {

    private LoanType type;

    private Double amount;

    private Double interestRate;

    private Integer duration;
}
