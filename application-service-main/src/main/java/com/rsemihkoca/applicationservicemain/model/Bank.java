package com.rsemihkoca.applicationservicemain.model;

import jakarta.persistence.*;
import lombok.Data;
import com.rsemihkoca.applicationservicemain.model.Constants.*;
import java.util.List;

@Data
@Entity
@Table(name = bankTable.TABLE_NAME)
public class Bank implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = bankTable.BANK_ID)
    private Long bankId;

    @Column(name = bankTable.NAME, unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy="bank", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Loan> loans;
}
