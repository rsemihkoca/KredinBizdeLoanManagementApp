package com.rsemihkoca.userservicemain.producer.dto;

import lombok.*;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class DeletedUser {
    private String email;
}