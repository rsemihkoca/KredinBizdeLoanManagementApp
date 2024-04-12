package com.rsemihkoca.applicationservicemain.dto.request;

import com.rsemihkoca.applicationservicemain.dto.request.interfaces.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
@Data
@Builder
public class CreateApplicationRequest implements HasEmail, HasLoanId, HasBankName {

    @NotBlank(message = "Name cannot be blank")
    private String email;

    @NotBlank(message = "LoanId cannot be blank")
    private Long loanId;

    @NotBlank(message = "Bank cannot be blank")
    private String bankName;
}