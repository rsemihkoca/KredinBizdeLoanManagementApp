package com.rsemihkoca.garantiservice.dto.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
