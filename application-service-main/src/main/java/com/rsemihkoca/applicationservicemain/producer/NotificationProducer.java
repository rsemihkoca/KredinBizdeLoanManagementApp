package com.rsemihkoca.applicationservicemain.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsemihkoca.applicationservicemain.producer.dto.Notification;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Scope("singleton")
public class NotificationProducer {

    private final ObjectMapper objectMapper;

    @Value(value = "${spring.kafka.template.default-topic}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public NotificationProducer(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @SneakyThrows
    public void sendNotification(Notification message) {
        kafkaTemplate.send(topicName, prepareNotification(message));
        kafkaTemplate.flush();
        log.info("Notification published to Kafka");
    }

    private String prepareNotification(Notification notification) {
        try {
            return objectMapper.writeValueAsString(notification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}