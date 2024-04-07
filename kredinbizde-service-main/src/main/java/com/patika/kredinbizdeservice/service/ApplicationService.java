package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.client.BankClientFactory;
import com.patika.kredinbizdeservice.client.BankServiceClient;
import com.patika.kredinbizdeservice.client.dto.request.BankApplicationRequest;
import com.patika.kredinbizdeservice.converter.ApplicationConverter;
import com.patika.kredinbizdeservice.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.enums.BankType;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.enums.NotificationType;
import com.patika.kredinbizdeservice.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationConverter applicationConverter;
    private final UserService userService;
    private final BankClientFactory bankClientFactory;
    private final NotificationProducer notificationProducer;

    public Application createApplication(ApplicationRequest request) {

        User user = userService.getByEmail(request.getEmail());
        log.info("user bulundu");

        Application application = applicationConverter.toApplication(request, user);

        Application savedApplication = applicationRepository.save(application);

        sendApplication(request, user);
        sendNotification(savedApplication);

        user.getApplicationList().add(savedApplication);

        return savedApplication;
    }

    private void sendApplication(ApplicationRequest request, User user) {
        BankType bankType = BankType.valueOf(request.getBankName().toUpperCase());
        BankServiceClient bankServiceClient = bankClientFactory.createBankClient(bankType);
        BankApplicationRequest bankApplicationRequest = new BankApplicationRequest();
        bankApplicationRequest.setUserId(user.getId());
        bankServiceClient.createApplication(bankApplicationRequest);
    }

    private void sendNotification(Application application) {
        NotificationDTO notificationDTO = getNotification(application);
        notificationProducer.sendNotification(notificationDTO);
    }

    private NotificationDTO getNotification(Application application) {
        return NotificationDTO.builder()
                .notificationType(NotificationType.EMAIL)
                .message("Kredi başvurunuz alınmıştır. Başvuru durumunuzu takip edebilirsiniz.")
                .email(application.getUser().getEmail())
                .build();
    }

    public List<Application> getAll(String email) {
        return applicationRepository.getAll(email);
    }

}
