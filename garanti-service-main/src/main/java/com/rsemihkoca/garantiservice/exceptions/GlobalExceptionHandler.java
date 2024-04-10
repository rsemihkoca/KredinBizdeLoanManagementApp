package com.rsemihkoca.garantiservice.exceptions;

import com.rsemihkoca.garantiservice.dto.response.ExceptionResponse;
import com.rsemihkoca.garantiservice.dto.response.GenericResponse;
import com.rsemihkoca.garantiservice.producer.TransactionProducer;
import com.rsemihkoca.garantiservice.producer.dto.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final TransactionProducer transactionProducer;

    public GlobalExceptionHandler(TransactionProducer transactionProducer) {
        this.transactionProducer = transactionProducer;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public void handleNoResourceFoundException(NoResourceFoundException exception) {
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<ExceptionResponse>> handleAllException(Exception exception) {
        log.error("An exception occurred: ", exception);
        transactionProducer.sendTransaction(prepareTransaction(exception));
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
                .sender("garanti-service-main")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .statusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

}
