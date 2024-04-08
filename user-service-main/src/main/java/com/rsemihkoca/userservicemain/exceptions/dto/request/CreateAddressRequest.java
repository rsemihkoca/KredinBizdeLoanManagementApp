package com.rsemihkoca.userservicemain.exceptions.dto.request;

import com.rsemihkoca.userservicemain.model.Address;
import com.rsemihkoca.userservicemain.model.Constants;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateAddressRequest {

    @NotBlank(message = "Address title cannot be blank")
    private String addressTitle;

    @NotBlank(message = "Address description cannot be blank")
    private String addressDescription;
}