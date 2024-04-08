package com.rsemihkoca.userservicemain.exceptions.dto.response;

import lombok.Data;


@Data
public class UserResponse implements java.io.Serializable{

    private String name;

    private Integer age;

    private String email;

    private AddressResponse address;
}
