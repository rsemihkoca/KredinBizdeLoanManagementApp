package com.patika.notificationservice.service;

import com.patika.notificationservice.dto.Notification;

public interface NotificationStrategy {
    void sendNotification(Notification message);
}
