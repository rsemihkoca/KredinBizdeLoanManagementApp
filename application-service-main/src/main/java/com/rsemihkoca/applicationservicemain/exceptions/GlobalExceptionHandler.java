package com.rsemihkoca.applicationservicemain.exceptions;

import com.rsemihkoca.applicationservicemain.dto.response.ExceptionResponse;
import com.rsemihkoca.applicationservicemain.dto.response.GenericResponse;
import com.rsemihkoca.applicationservicemain.producer.GenericKafkaProducer;
import com.rsemihkoca.applicationservicemain.producer.dto.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final GenericKafkaProducer genericKafkaProducer;

    public GlobalExceptionHandler(GenericKafkaProducer genericKafkaProducer) {
        this.genericKafkaProducer = genericKafkaProducer;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<ExceptionResponse>> handleAllException(Exception exception) {
        log.error("exception occurred. {}", exception.getMessage());
        genericKafkaProducer.sendTransaction(prepareTransaction(exception));

        GenericResponse<ExceptionResponse> response = GenericResponse.error(prepareExceptionResponse(exception));
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    private ExceptionResponse prepareExceptionResponse(Exception exception) {
        return ExceptionResponse.builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
    }

    private Transaction prepareTransaction(Exception exception) {
        return Transaction.builder()
                .errorMessage(exception.getMessage())
                .sender("application-service-main")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .statusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

}
