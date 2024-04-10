package com.rsemihkoca.userservicemain.exceptions;

import com.rsemihkoca.userservicemain.exceptions.dto.ExceptionResponse;
import com.rsemihkoca.userservicemain.producer.TransactionProducer;
import com.rsemihkoca.userservicemain.producer.dto.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

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
                .sender("user-service-main")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .statusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

}
