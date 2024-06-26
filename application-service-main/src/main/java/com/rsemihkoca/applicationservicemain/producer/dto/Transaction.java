package com.rsemihkoca.applicationservicemain.producer.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class Transaction {
    private String errorMessage;
    private String sender;
    private String statusCode;
    private HttpStatus httpStatus;
    private String timestamp;
}