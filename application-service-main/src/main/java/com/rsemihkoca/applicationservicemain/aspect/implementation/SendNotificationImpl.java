package com.rsemihkoca.applicationservicemain.aspect.implementation;

import com.rsemihkoca.applicationservicemain.dto.request.interfaces.CanSendNotification;
import com.rsemihkoca.applicationservicemain.enums.NotificationContent;
import com.rsemihkoca.applicationservicemain.listener.KafkaConsumerListener;
import com.rsemihkoca.applicationservicemain.producer.GenericKafkaProducer;
import com.rsemihkoca.applicationservicemain.producer.dto.Notification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.rsemihkoca.applicationservicemain.aspect.signature.SendNotification;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class SendNotificationImpl {

    private final GenericKafkaProducer genericKafkaProducer;
    private final KafkaConsumerListener kafkaConsumerListener;

    //    @AfterReturning(
//            value = "@annotation(com.rsemihkoca.applicationservicemain.aspect.signature.SendNotification) && execution(* *(..))",
//            returning = "response"
//    )
//    public <T extends CanSendNotification> void sendNotification(T response) {
//        Notification notification = response.getNotification(sendNotification.content());
//        genericKafkaProducer.sendNotification(notification);
//    }
    @Pointcut("@annotation(sendNotification)")
    public void sendNotificationPointcut(SendNotification sendNotification) {
    }

    @AfterReturning(pointcut = "sendNotificationPointcut(sendNotification)", returning = "response", argNames = "sendNotification,response")
    public void sendNotificationAdvice(SendNotification sendNotification, Object response) {
        NotificationContent content = sendNotification.content();
        Notification notification = ((CanSendNotification) response).getNotification(content);
        genericKafkaProducer.sendNotification(notification);
    }
}


