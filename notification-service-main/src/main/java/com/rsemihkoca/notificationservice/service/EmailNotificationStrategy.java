package com.rsemihkoca.notificationservice.service;

import com.rsemihkoca.notificationservice.dto.Notification;
import com.rsemihkoca.notificationservice.dto.enums.NotificationContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
@Slf4j
@Scope(value = "prototype")
public class EmailNotificationStrategy implements NotificationStrategy {


    private static final String APPLICATION_CREATED_HTML;

    static {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader("templates/ApplicationCreated.html"));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        APPLICATION_CREATED_HTML = contentBuilder.toString();
    }

    private final EmailService emailService;

    public EmailNotificationStrategy(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void sendNotification(Notification message) {
        log.info("Email gönderildi: {}", message);

        switch (NotificationContent.valueOf(message.getMessage())) {
//            case APPLICATION_CREATED -> applicationCreated(message);
            case APPLICATION_CREATED -> log.info("Email doğrulama maili gönderildi.");
            case APPLICATION_UPDATED -> log.info("Şifre sıfırlama maili gönderildi.");
            case APPLICATION_DELETED -> log.info("Email bildirim maili gönderildi.");
        }
    }

    private void applicationCreated(Notification message) {
        log.info("Email doğrulama maili gönderildi.");
        // html in templates/ApplciationCreated.html
        try {
            emailService.sendEmail(message.getChannel(), "Doğrulama maili", APPLICATION_CREATED_HTML);
        }
        catch (Exception e) {
            log.error("Email gönderilemedi: {}", e.getMessage());
        }
    }
}