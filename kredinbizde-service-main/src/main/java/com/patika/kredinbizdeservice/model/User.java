package com.patika.kredinbizdeservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String surname;
    private String email; //bir email ile bir kere kayıt olunabilir.
    private String password; //hash fonskiyonlarından biri ile hashlanecek.
    private String phoneNumber;
    private Boolean isActive;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Application> applicationList;

    public User() {
        this.id = 0L;
        this.name = null;
        this.surname = null;
        this.email = null;
        this.password = null;
        this.phoneNumber = null;
        this.isActive = null;
    }

    public User(Long id,
                String name, String surname, String email, String password, String phoneNumber, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }

}
