package com.rsemihkoca.notificationservice.dto;

import com.rsemihkoca.notificationservice.dto.enums.NotificationType;
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
