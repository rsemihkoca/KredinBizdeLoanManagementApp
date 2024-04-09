package com.rsemihkoca.applicationservicemain.model;

import com.rsemihkoca.applicationservicemain.model.Constants.*;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = userTable.TABLE_NAME)
public class User implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = userTable.USER_ID)
    private Long userId;

    @Column(name = userTable.EMAIL, unique = true, nullable = false)
    private String email;

    @OneToMany()
    @JoinColumn(name = applicationTable.APPLICATION_ID)
    private List<Application> applications;
}


