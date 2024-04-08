package com.rsemihkoca.userservicemain.exceptions.dto.request;

import com.rsemihkoca.userservicemain.model.Address;
import com.rsemihkoca.userservicemain.model.Constants;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateAddressRequest {

    private String addressTitle;

    private String addressDescription;

}
