package com.rsemihkoca.applicationservicemain.model;


import com.rsemihkoca.applicationservicemain.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;
import com.rsemihkoca.applicationservicemain.model.Constants.*;

import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = applicationTable.TABLE_NAME)
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = applicationTable.APPLICATION_ID)
    private Long applicationId;

    @OneToOne
    @JoinColumn(name = loanTable.LOAN_ID, unique = true)
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = userTable.USER_ID, nullable = false)
    private User user;

    @Column(name = applicationTable.IS_ACTIVE, columnDefinition = "boolean default false")
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(name = applicationTable.APPLICATION_STATUS)
    private ApplicationStatus applicationStatus;

}
