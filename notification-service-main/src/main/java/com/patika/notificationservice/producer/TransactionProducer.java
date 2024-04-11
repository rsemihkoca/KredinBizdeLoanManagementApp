package com.patika.notificationservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patika.notificationservice.producer.dto.Transaction;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Scope("singleton")
public class TransactionProducer {

    private final ObjectMapper objectMapper;

    @Value(value = "${spring.kafka.producer.error-topic}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public TransactionProducer(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @SneakyThrows
    public void sendTransaction(Transaction message) {
        kafkaTemplate.send(topicName, prepareTransaction(message));
        kafkaTemplate.flush();
        log.info("Transaction published to Kafka");
    }

    private String prepareTransaction(Transaction transaction) {
        try {
            return objectMapper.writeValueAsString(transaction);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}