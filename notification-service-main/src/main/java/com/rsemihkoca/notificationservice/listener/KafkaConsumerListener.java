package com.rsemihkoca.notificationservice.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsemihkoca.notificationservice.dto.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {

    private final ObjectMapper objectMapper;

    private final NotificationListener notificationListener;

    public KafkaConsumerListener(ObjectMapper objectMapper, NotificationListener notificationListener,
                                 NotificationListener notificationListener1) {
        this.objectMapper = objectMapper;
        this.notificationListener = notificationListener1;
    }

    @KafkaListener(topics = "${spring.kafka.producer.notification-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        System.out.printf("Received Messasge: [%s] %n", message);
        try {
            Notification transaction = objectMapper.readValue(message, Notification.class);
            Notification save = notificationListener.sendNotification(transaction);
            System.out.println("Notification sent to successfully: " + save.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}