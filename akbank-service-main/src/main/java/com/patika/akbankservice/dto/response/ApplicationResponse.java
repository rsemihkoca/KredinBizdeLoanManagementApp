package com.patika.akbankservice.dto.response;

import com.patika.akbankservice.enums.BankApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApplicationResponse {

    private Long userId;
    private LocalDateTime createDate;
    private BankApplicationStatus applicationStatus;
}
