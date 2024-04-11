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

    @Column(name = loanTable.LOAN_ID, nullable = false)
    private Long loanId;

    @Column(name = applicationTable.BANK_NAME, nullable = false)
    private String bankName;

    @Column(name = userTable.EMAIL, nullable = false)
    private String userEmail;

    @Column(name = applicationTable.IS_ACTIVE, columnDefinition = "boolean default false")
    private boolean isActive;

    @Column(name = applicationTable.APPLICATION_DATE)
    private String applicationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = applicationTable.APPLICATION_STATUS)
    private ApplicationStatus applicationStatus;

}
