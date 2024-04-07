package com.patika.kredinbizdeservice.exceptions;

import com.patika.kredinbizdeservice.exceptions.dto.ExceptionResponse;
import com.patika.kredinbizdeservice.producer.TransactionProducer;
import com.patika.kredinbizdeservice.producer.dto.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final TransactionProducer transactionProducer;

    public GlobalExceptionHandler(TransactionProducer transactionProducer) {
        this.transactionProducer = transactionProducer;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllException(Exception exception) {
        log.error("exception occurred. {0}", exception.getCause());
        transactionProducer.sendTransaction(prepareTransaction(exception));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(prepareExceptionResponse(exception));
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
                .sender("kredinbizde-service-main")
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .timestamp(System.currentTimeMillis())
                .build();
    }

}
