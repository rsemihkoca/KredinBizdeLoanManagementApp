package com.rsemihkoca.userservicemain.exceptions.dto.request;

import com.rsemihkoca.userservicemain.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateUserRequest {

    private String name;

    private Integer age;

    private String email;

    private Address address;

}
