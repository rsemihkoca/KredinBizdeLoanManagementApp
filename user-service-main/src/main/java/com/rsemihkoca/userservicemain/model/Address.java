package com.rsemihkoca.userservicemain.model;

import jakarta.persistence.*;
import lombok.*;
import com.rsemihkoca.userservicemain.model.Constants.*;

import java.io.Serializable;

@Entity
@Data
@Table(name = addressTable.TABLE_NAME)
@Builder
@AllArgsConstructor
@NoArgsConstructor
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



