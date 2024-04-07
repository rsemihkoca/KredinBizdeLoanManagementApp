package com.patika.notificationservice.listener;

import com.patika.notificationservice.dto.NotificationDTO;
import com.patika.notificationservice.dto.enums.NotificationType;
import com.patika.notificationservice.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void sendNotification(NotificationDTO notificationDTO) {
        log.info("Notification received: {}", notificationDTO);
        NotificationStrategy notificationStrategy = getNotificationType(notificationDTO);
        String message = notificationDTO.getMessage();
        notificationService.setNotificationStrategy(notificationStrategy);
        notificationService.sendNotification(message);

    }

    private NotificationStrategy getNotificationType(NotificationDTO notificationDTO) {
        NotificationType notificationType = notificationDTO.getNotificationType();
        return switch (notificationType) {
            case SMS -> smsNotificationStrategy;
            case EMAIL -> emailNotificationStrategy;
            case MOBILE_NOTIFICATION -> mobileNotificationStrategy;
        };
    }


}
