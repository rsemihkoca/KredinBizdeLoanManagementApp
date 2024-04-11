package com.rsemihkoca.notificationservice.service;

import com.rsemihkoca.notificationservice.dto.Notification;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class NotificationService {
    private NotificationStrategy notificationStrategy;


    public void setNotificationStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    public void sendNotification(Notification message) {
        assert notificationStrategy != null;
        notificationStrategy.sendNotification(message);
    }
}