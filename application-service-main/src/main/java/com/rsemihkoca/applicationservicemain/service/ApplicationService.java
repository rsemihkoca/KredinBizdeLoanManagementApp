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
import com.rsemihkoca.applicationservicemain.producer.NotificationProducer;
import com.rsemihkoca.applicationservicemain.producer.dto.NotificationDTO;
import com.rsemihkoca.applicationservicemain.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.rsemihkoca.applicationservicemain.dto.request.ApplicationRequest;

import java.io.Serializable;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserServiceClient userServiceClient;
    private final BankServiceClient bankServiceClient;
    private final NotificationProducer notificationProducer;

    private final ModelMapper modelMapper;

    public ApplicationResponse createApplication(ApplicationRequest request) {

        String userEmail = getUser(request);
        log.info("User found");

        Long loanId = getLoan(request);
        log.info("Loan found");

        Application newApplicatin = Application.builder()
                .userEmail(userEmail)
                .loanId(loanId)
                .isActive(true)
                .applicationStatus(ApplicationStatus.IN_PROGRESS)
                .build();

        Application savedApplication = applicationRepository.save(newApplicatin);
        sendNotification(savedApplication, NotificationContent.APPLICATION_CREATED);
        return modelMapper.map(savedApplication, ApplicationResponse.class);
    }


    private Long getLoan(ApplicationRequest request) {
        Long loanId = request.getLoanId();
        String bank = request.getBankName();
        GenericResponse<List<MergedLoanResponse>> loanResponse = bankServiceClient.getAll().getBody();
        // check if this loan id and bank is in the list
        if (loanResponse == null) {
            throw new RuntimeException("BankService returned empty response");
        }
        List<MergedLoanResponse> data = loanResponse.getData();
        MergedLoanResponse mergedLoanResponse = data.stream()
                .filter(loan -> loan.getLoanId().equals(loanId) && loan.getBankName().equals(bank))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        return mergedLoanResponse.getLoanId();
    }

    private String getUser(ApplicationRequest request) {
        String email = request.getEmail();
        ResponseEntity<UserResponse> user = userServiceClient.getByEmail(email);
        if (user.getBody() == null) {
            throw new RuntimeException("User not found");
        }
        return user.getBody().getEmail();
    }



    private void sendNotification(Application application, NotificationContent content) {
        NotificationDTO notificationDTO = getNotification(application, content);
        notificationProducer.sendNotification(notificationDTO);
    }

    private NotificationDTO getNotification(Application application, NotificationContent content) {
        return NotificationDTO.builder()
                .notificationType(NotificationType.EMAIL)
                .channel(application.getUserEmail())
                .message(content.toString())
                .build();
    }

    public List<ApplicationResponse> getByEmail(String email) {
        return applicationRepository.findByUserEmail(email);
    }

    public List<ApplicationResponse> getAll() {
        return applicationRepository.findAll();
    }

}
