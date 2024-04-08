package com.rsemihkoca.applicationservicemain.model;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

import static com.rsemihkoca.userservicemain.model.Constants.*;

@Entity
@Data
@Table(name = userTable.TABLE_NAME)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = userTable.USER_ID)
    private Long userId;

    @Column(name = userTable.NAME, unique = false, nullable = false)
    private String name;

    @Column(name = userTable.AGE, unique = false, nullable = false)
    private Integer age;

    @Column(name = userTable.EMAIL, unique = true, nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = addressTable.ADDRESS_ID, nullable = true, unique = false)
    private Address address;
}

