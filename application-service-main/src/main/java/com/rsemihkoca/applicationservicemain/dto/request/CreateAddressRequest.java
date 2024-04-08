package com.rsemihkoca.applicationservicemain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CreateAddressRequest {

    @NotBlank(message = "Address title cannot be blank")
    private String addressTitle;

    @NotBlank(message = "Address description cannot be blank")
    private String addressDescription;
}