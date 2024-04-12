package com.rsemihkoca.userservicemain.exceptions.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse implements java.io.Serializable{

    private String addressTitle;

    private String addressDescription;
}
