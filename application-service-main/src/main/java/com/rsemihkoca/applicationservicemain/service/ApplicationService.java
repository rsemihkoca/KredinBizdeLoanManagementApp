package com.rsemihkoca.applicationservicemain.service;


import com.rsemihkoca.applicationservicemain.client.bankservice.BankServiceClient;
import com.rsemihkoca.applicationservicemain.client.userservice.UserServiceClient;
import com.rsemihkoca.applicationservicemain.dto.response.ApplicationResponse;
import com.rsemihkoca.applicationservicemain.dto.response.GenericResponse;
import com.rsemihkoca.applicationservicemain.dto.response.MergedLoanResponse;
import com.rsemihkoca.applicationservicemain.dto.response.UserResponse;
import com.rsemihkoca.applicationservicemain.enums.ApplicationStatus;
import com.rsemihkoca.applicationservicemain.enums.NotificationContent;
import com.rsemihkoca.applicationservicemain.enums.NotificationType;
import com.rsemihkoca.applicationservicemain.model.Application;
import com.rsemihkoca.applicationservicemain.model.Constants;
import com.rsemihkoca.applicationservicemain.producer.NotificationProducer;
import com.rsemihkoca.applicationservicemain.producer.dto.Notification;
import com.rsemihkoca.applicationservicemain.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.rsemihkoca.applicationservicemain.dto.request.ApplicationRequest;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserServiceClient userServiceClient;
    private final BankServiceClient bankServiceClient;
    private final NotificationProducer notificationProducer;

    private final ModelMapper modelMapper;

    @CacheEvict(value = Constants.applicationTable.TABLE_NAME, allEntries = true)
    public ApplicationResponse createApplication(ApplicationRequest request) {

        String userEmail = getUser(request);
        log.info("User found");

        Long loanId = getLoan(request);
        log.info("Loan found");

        // check if there is an active application for this user for same loan
        // :TODO

        Application newApplication = getNewApplication(request, userEmail, loanId);
        Application savedApplication = applicationRepository.save(newApplication);

        sendNotification(savedApplication, NotificationContent.APPLICATION_CREATED);

        return modelMapper.map(savedApplication, ApplicationResponse.class);
    }

    private static Application getNewApplication(ApplicationRequest request, String userEmail, Long loanId) {
        return Application.builder()
                .userEmail(userEmail)
                .loanId(loanId)
                .isActive(true)
                .bankName(request.getBankName().toUpperCase())
                .applicationStatus(ApplicationStatus.IN_PROGRESS)
                .applicationDate(LocalDateTime.now().toString())
                .build();
    }



    private Long getLoan(ApplicationRequest request) {
        Long loanId = request.getLoanId();
        String bank = request.getBankName().toUpperCase();
        GenericResponse<List<MergedLoanResponse>> loanResponse = bankServiceClient.getAll().getBody();
        // check if this loan id and bank is in the list
        if (loanResponse == null) {
            throw new RuntimeException("BankService returned empty response");
        }
        List<MergedLoanResponse> data = loanResponse.getData();
        MergedLoanResponse mergedLoanResponse = data.stream()
                .filter(loan -> loan.getLoanId().equals(loanId) && loan.getBankName().toUpperCase().equals(bank))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        return mergedLoanResponse.getLoanId();
    }

    private String getUser(ApplicationRequest request) {
        String email = request.getEmail();
        ResponseEntity<GenericResponse<UserResponse>> user = userServiceClient.getByEmail(email);
        if (user.getBody().getData() == null) {
            throw new RuntimeException("User not found");
        }
        return user.getBody().getData().getEmail();
    }



    private void sendNotification(Application application, NotificationContent content) {
        Notification notification = getNotification(application, content);
        notificationProducer.sendNotification(notification);
    }

    private Notification getNotification(Application application, NotificationContent content) {
        return Notification.builder()
                .notificationType(NotificationType.EMAIL)
                .channel(application.getUserEmail())
                .message(content.toString())
                .build();
    }

    // Check only return active applications
    //@Cacheable(value = Constants.applicationTable.TABLE_NAME, key = "#email")
    public List<ApplicationResponse> getByEmail(String email) {
        List<Application> applications = applicationRepository.findActiveApplicationsByUserEmail(email);
        return applications.stream()
                .map(application -> modelMapper.map(application, ApplicationResponse.class))
                .collect(Collectors.toList());
    }
    // Check only return active applications

    @Cacheable(value = Constants.applicationTable.TABLE_NAME)
    public List<ApplicationResponse> getAll() {
        List<Application> applications = applicationRepository.findAllActiveApplications();

        return applications.stream()
                .map(application -> modelMapper.map(application, ApplicationResponse.class))
                .collect(Collectors.toList());
    }
}
