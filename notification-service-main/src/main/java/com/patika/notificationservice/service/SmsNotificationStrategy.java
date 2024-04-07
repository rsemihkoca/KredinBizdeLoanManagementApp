package com.patika.notificationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Scope(value = "prototype")
public class SmsNotificationStrategy implements NotificationStrategy{
    @Override
    public void sendNotification(String message) {
        log.info("Sms gönderildi: {}", message);
    }
}
