package com.patika.garantiservice.entity;

import com.patika.garantiservice.enums.BankApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = Constants.applicationTable.TABLE_NAME)
public class Application implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.applicationTable.APPLICATION_ID)
    private Long applicationId;

    @Column(name = Constants.applicationTable.USER_ID)
    private Long userId;

    @Column(name = Constants.applicationTable.CREATE_DATE)
    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    @Column(name = Constants.applicationTable.APPLICATION_STATUS)
    private BankApplicationStatus applicationStatus;

}