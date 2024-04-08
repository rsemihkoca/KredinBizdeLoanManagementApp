package com.patika.kredinbizdeservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patika.kredinbizdeservice.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application")
public class Application extends Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   /* @OneToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;*/

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_status")
    private ApplicationStatus applicationStatus;

}
