package com.rsemihkoca.notificationservice.service;

import com.rsemihkoca.notificationservice.dto.Notification;

public interface NotificationStrategy {
    void sendNotification(Notification message);
}
