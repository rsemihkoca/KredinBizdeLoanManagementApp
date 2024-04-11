package com.rsemihkoca.applicationservicemain.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsemihkoca.applicationservicemain.service.ApplicationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.rsemihkoca.applicationservicemain.producer.dto.DeletedUser;

@Component
public class KafkaConsumerListener {

    private final ObjectMapper objectMapper;

    private final ApplicationService applicationService;

    public KafkaConsumerListener(ObjectMapper objectMapper, ApplicationService applicationService) {
        this.objectMapper = objectMapper;
        this.applicationService = applicationService;
    }

    @KafkaListener(topics = "${spring.kafka.producer.user-deletion-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        System.out.printf("Received Messasge: [%s] %n", message);
        try {
            DeletedUser user = objectMapper.readValue(message, DeletedUser.class);
            System.out.println("DeletedUser a user " + user);
            Integer count = applicationService.deactivateApplicationsByUserEmail(user.getEmail());
            System.out.println("Deactivated " + count + " applications");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}