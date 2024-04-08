package com.rsemihkoca.applicationservicemain.model;

import com.rsemihkoca.applicationservicemain.model.Constants.*;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = addressTable.TABLE_NAME)
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = addressTable.ADDRESS_ID)
    private Long addressId;

    @Column(name = addressTable.ADDRESS_TITLE)
    private String addressTitle;

    @Column(name = addressTable.ADDRESS_DESCRIPTION)
    private String addressDescription;

}



