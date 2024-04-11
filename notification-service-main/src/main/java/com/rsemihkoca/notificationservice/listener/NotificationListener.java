package com.rsemihkoca.notificationservice.listener;

import com.rsemihkoca.notificationservice.dto.Notification;
import com.rsemihkoca.notificationservice.dto.enums.NotificationType;
import com.rsemihkoca.notificationservice.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationListener {

    private final NotificationService notificationService;

    private final SmsNotificationStrategy smsNotificationStrategy;
    private final EmailNotificationStrategy emailNotificationStrategy;
    private final MobileNotificationStrategy mobileNotificationStrategy;

    public NotificationListener(NotificationService notificationService,
                                SmsNotificationStrategy smsNotificationStrategy,
                                EmailNotificationStrategy emailNotificationStrategy,
                                MobileNotificationStrategy mobileNotificationStrategy) {
        this.notificationService = notificationService;
        this.smsNotificationStrategy = smsNotificationStrategy;
        this.emailNotificationStrategy = emailNotificationStrategy;
        this.mobileNotificationStrategy = mobileNotificationStrategy;
    }

    public Notification sendNotification(Notification notification) {
        log.info("Notification received: {}", notification);
        NotificationStrategy notificationStrategy = getNotificationType(notification);
        notificationService.setNotificationStrategy(notificationStrategy);
        notificationService.sendNotification(notification);
        return notification;

    }

    private NotificationStrategy getNotificationType(Notification notification) {
        NotificationType notificationType = notification.getNotificationType();
        return switch (notificationType) {
            case SMS -> smsNotificationStrategy;
            case EMAIL -> emailNotificationStrategy;
            case MOBILE_NOTIFICATION -> mobileNotificationStrategy;
        };
    }


}
