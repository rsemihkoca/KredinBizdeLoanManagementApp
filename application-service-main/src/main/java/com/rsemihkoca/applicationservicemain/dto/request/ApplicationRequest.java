package com.rsemihkoca.applicationservicemain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
@Data
@Builder
public class ApplicationRequest {

    @NotBlank(message = "Name cannot be blank")
    private String email;

    @NotBlank(message = "LoanId cannot be blank")
    private Long loanId;

    @NotBlank(message = "Bank cannot be blank")
    private String bankName;
}