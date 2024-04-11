package com.rsemihkoca.applicationservicemain.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsemihkoca.applicationservicemain.producer.dto.Notification;
import com.rsemihkoca.applicationservicemain.producer.dto.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serializer;


@Service
@Slf4j
public class GenericKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value(value = "${spring.kafka.producer.error-topic}")
    private String errorTopicName;

    @Value(value = "${spring.kafka.producer.notification-topic}")
    private String notificationTopicName;

    public GenericKafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendNotification(Notification notification) {
        send(notificationTopicName, notification);
    }

    public void sendTransaction(Transaction transaction) {
        send(errorTopicName, transaction);
    }

    public <T> void send(String topic, T object) {
        String jsonMessage = prepareObject(object);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, jsonMessage);
        kafkaTemplate.send(record);
        log.info("Message published to Kafka");
        kafkaTemplate.flush();
    }

    private <T> String prepareObject(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

