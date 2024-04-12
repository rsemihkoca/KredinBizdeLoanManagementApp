package com.rsemihkoca.applicationservicemain.dto.response;

import com.rsemihkoca.applicationservicemain.dto.request.interfaces.CanSendNotification;
import com.rsemihkoca.applicationservicemain.enums.NotificationContent;
import com.rsemihkoca.applicationservicemain.enums.NotificationType;
import com.rsemihkoca.applicationservicemain.producer.dto.Notification;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponse implements java.io.Serializable, CanSendNotification {

    private String userEmail;
    private MergedLoanResponse mergedLoanResponse;
    private String applicationDate;
    private String applicationStatus;

    @Override
    public Notification getNotification(NotificationContent content) {
        return Notification.builder()
                .notificationType(NotificationType.EMAIL)
                .channel(this.getUserEmail())
                .message(content.toString())
                .build();
    }
}



