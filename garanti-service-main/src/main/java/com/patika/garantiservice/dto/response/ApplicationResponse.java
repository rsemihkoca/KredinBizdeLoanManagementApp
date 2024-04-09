package com.patika.garantiservice.dto.response;

import com.patika.garantiservice.enums.BankApplicationStatus;
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
