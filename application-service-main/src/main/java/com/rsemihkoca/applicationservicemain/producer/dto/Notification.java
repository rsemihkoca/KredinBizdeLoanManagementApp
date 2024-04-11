package com.rsemihkoca.applicationservicemain.producer.dto;

import com.rsemihkoca.applicationservicemain.enums.NotificationType;
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
