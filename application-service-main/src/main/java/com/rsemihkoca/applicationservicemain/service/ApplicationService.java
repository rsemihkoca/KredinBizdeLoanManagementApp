package com.rsemihkoca.applicationservicemain.service;


import com.rsemihkoca.applicationservicemain.aspect.signature.CheckApplicationAbsence;
import com.rsemihkoca.applicationservicemain.aspect.signature.CheckLoanExistence;
import com.rsemihkoca.applicationservicemain.aspect.signature.CheckUserExistence;
import com.rsemihkoca.applicationservicemain.aspect.signature.SendNotification;
import com.rsemihkoca.applicationservicemain.dto.response.*;
import com.rsemihkoca.applicationservicemain.enums.*;
import com.rsemihkoca.applicationservicemain.model.ApplicationPipeline;
import com.rsemihkoca.applicationservicemain.model.*;
import com.rsemihkoca.applicationservicemain.producer.*;
import com.rsemihkoca.applicationservicemain.producer.dto.Notification;
import com.rsemihkoca.applicationservicemain.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.rsemihkoca.applicationservicemain.dto.request.CreateApplicationRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationPipeline applicationPipeline;
    private final ModelMapper modelMapper;

    @CheckUserExistence
    @CheckLoanExistence
    @CheckApplicationAbsence
    @SendNotification(content = NotificationContent.APPLICATION_CREATED)
    public ApplicationResponse createApplication(CreateApplicationRequest request) {
        String userEmail = applicationPipeline.getCurrentUserEmail();
        MergedLoanResponse loan = applicationPipeline.getCurrentLoanRequest();

        Application newApplication = getNewApplication(request, userEmail, loan.getLoanId());
        Application savedApplication = applicationRepository.save(newApplication);

        ApplicationResponse applicationResponse = modelMapper.map(savedApplication, ApplicationResponse.class);
        applicationResponse.setMergedLoanResponse(loan);

        return applicationResponse;
    }

    private static Application getNewApplication(CreateApplicationRequest request, String userEmail, Long loanId) {
        return Application.builder()
                .userEmail(userEmail)
                .loanId(loanId)
                .isActive(true)
                .bankName(request.getBankName().toUpperCase())
                .applicationStatus(ApplicationStatus.CREATED)
                .applicationDate(LocalDateTime.now().toString())
                .build();
    }

    @CheckUserExistence
    public List<ApplicationResponse> getByEmail(String email) {
        List<Application> applications = applicationRepository.findActiveApplicationsByUserEmail(email);
        return applications.stream()
                .map(application -> modelMapper.map(application, ApplicationResponse.class))
                .collect(Collectors.toList());
    }

    public List<ApplicationResponse> getAll() {
        List<Application> applications = applicationRepository.findAllActiveApplications();

        return applications.stream()
                .map(application -> modelMapper.map(application, ApplicationResponse.class))
                .collect(Collectors.toList());
    }

    public Application getApplicationByUserEmailAndLoanId(String userEmail, Long loanId) {
        return applicationRepository.findActiveApplicationByUserEmailAndLoanId(userEmail, loanId);
    }

    public Integer deactivateApplicationsByUserEmail(String email) {
        List<Application> applications = applicationRepository.findActiveApplicationsByUserEmail(email);
        applications.forEach(application -> {
            application.setActive(false);
            applicationRepository.save(application);
        });

        return applications.size();
    }
}
