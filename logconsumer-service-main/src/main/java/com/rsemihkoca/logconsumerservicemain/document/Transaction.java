package com.rsemihkoca.logconsumerservicemain.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

@Document(collection = "transactions")
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
    private long timestamp;

}
