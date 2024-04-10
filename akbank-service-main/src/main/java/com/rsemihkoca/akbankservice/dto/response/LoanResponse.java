package com.rsemihkoca.akbankservice.dto.response;

import com.rsemihkoca.akbankservice.entity.Constants;
import com.rsemihkoca.akbankservice.enums.BankApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoanResponse implements Serializable{

        private LocalDate CreateDate;

        private LocalDate UpdateDate;

        private String Type;

        private Double Amount;

        private Double InterestRate;

        private Integer Duration;

}
