package com.patika.notificationservice.service;

import com.patika.notificationservice.dto.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Scope(value = "prototype")
public class SmsNotificationStrategy implements NotificationStrategy{
    @Override
    public void sendNotification(Notification message) {
        log.info("Sms gönderildi: {}", message);
    }
}
