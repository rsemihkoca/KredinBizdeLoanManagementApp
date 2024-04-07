package com.patika.notificationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Scope(value = "prototype")
public class MobileNotificationStrategy implements NotificationStrategy{
    @Override
    public void sendNotification(String message) {
        System.out.println("Mobile notification sent: " + message);
    }
}
