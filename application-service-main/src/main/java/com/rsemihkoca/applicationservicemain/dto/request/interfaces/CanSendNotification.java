package com.rsemihkoca.applicationservicemain.dto.request.interfaces;

import com.rsemihkoca.applicationservicemain.enums.NotificationContent;
import com.rsemihkoca.applicationservicemain.producer.dto.Notification;

public interface CanSendNotification {

    Notification getNotification(NotificationContent notificationContent);
}
