package com.patika.kredinbizdeservice.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Audit {

    @Column(name = "create_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;
}
