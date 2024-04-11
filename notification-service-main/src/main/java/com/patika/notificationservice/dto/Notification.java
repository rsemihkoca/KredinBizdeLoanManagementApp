package com.patika.notificationservice.dto;

import com.patika.notificationservice.dto.enums.NotificationType;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Notification {

    private NotificationType notificationType;
    private String message;
    private String channel;

}
