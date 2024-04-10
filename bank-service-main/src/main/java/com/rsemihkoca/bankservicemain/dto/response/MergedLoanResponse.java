package com.rsemihkoca.bankservicemain.dto.response;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MergedLoanResponse implements Serializable {

    private Long loanId;

    private String bankName;

    private String type;

    private Double amount;

    private Double interestRate;

    private Integer duration;
}
