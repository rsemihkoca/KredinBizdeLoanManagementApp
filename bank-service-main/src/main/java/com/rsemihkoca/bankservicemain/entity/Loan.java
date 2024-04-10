package com.rsemihkoca.bankservicemain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loan implements Serializable {

    private String bank;

    private String createDate;

    private String updateDate;

    private String type;

    private Double amount;

    private Double interestRate;

    private Integer duration;

}