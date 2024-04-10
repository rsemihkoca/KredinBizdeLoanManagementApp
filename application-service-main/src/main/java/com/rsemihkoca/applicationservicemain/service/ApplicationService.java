package com.rsemihkoca.applicationservicemain.service;


import com.rsemihkoca.applicationservicemain.client.BankServiceClient;
import com.rsemihkoca.applicationservicemain.client.userservice.UserServiceClient;
import com.rsemihkoca.applicationservicemain.dto.request.BankApplicationRequest;
import com.rsemihkoca.applicationservicemain.dto.response.UserResponse;
import com.rsemihkoca.applicationservicemain.enums.ApplicationStatus;
import com.rsemihkoca.applicationservicemain.enums.BankType;
import com.rsemihkoca.applicationservicemain.enums.NotificationType;
import com.rsemihkoca.applicationservicemain.model.Application;
import com.rsemihkoca.applicationservicemain.model.Loan;
import com.rsemihkoca.applicationservicemain.producer.dto.NotificationDTO;
import com.rsemihkoca.applicationservicemain.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.rsemihkoca.applicationservicemain.dto.request.ApplicationRequest;
import com.rsemihkoca.applicationservicemain.client.ClientFactory;

import java.io.Serializable;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final LoanService loanService;
    private final UserServiceClient userServiceClient;
    private final ClientFactory clientFactory;
    private final ModelMapper modelMapper;

    public Application createApplication(ApplicationRequest request) {

        String userEmail = getUser(request);
        log.info("User found");

        Loan loan = getLoan(request);
        log.info("Loan found");

        Application newApplicatin = Application.builder()
                .userEmail(userEmail)
                .loan(loan)
                .isActive(true)
                .applicationStatus(ApplicationStatus.IN_PROGRESS)
                .build();

        Application savedApplication = applicationRepository.save(newApplicatin);

//        sendNotification(savedApplication);

        return savedApplication;
    }


    private Loan getLoan(ApplicationRequest request) {
        Loan loan = loanService.getLoanById(request.getLoanId());
        if (loan == null) {
            throw new RuntimeException("Loan not found");
        }
        return loan;
    }

    private String getUser(ApplicationRequest request) {
        String email = request.getEmail();
        ResponseEntity<UserResponse> user = userServiceClient.getByEmail(email);
        if (user.getBody() == null) {
            throw new RuntimeException("User not found");
        }
        return user.getBody().getEmail();
    }



//    private void sendNotification(Application application) {
//        NotificationDTO notificationDTO = getNotification(application);
//        notificationProducer.sendNotification(notificationDTO);
//    }

    private NotificationDTO getNotification(Application application) {
        return NotificationDTO.builder()
                .notificationType(NotificationType.EMAIL)
                .message("Kredi başvurunuz alınmıştır. Başvuru durumunuzu takip edebilirsiniz.")
                .email(application.getUserEmail())
                .build();
    }

    public List<Application> getByEmail(String email) {
        return applicationRepository.findByUserEmail(email);
    }

    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

}
