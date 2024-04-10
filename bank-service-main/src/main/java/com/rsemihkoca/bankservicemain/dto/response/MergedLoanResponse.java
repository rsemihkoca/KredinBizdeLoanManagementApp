package com.rsemihkoca.bankservicemain.dto.response;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponse implements Serializable {


    private String type;

    private Double amount;

    private Double interestRate;

    private Integer duration;
    private String createDate;

    private String updateDate;
}
