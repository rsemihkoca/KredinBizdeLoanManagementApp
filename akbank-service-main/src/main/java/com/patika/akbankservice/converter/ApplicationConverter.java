package com.patika.akbankservice.converter;

import com.patika.akbankservice.dto.request.ApplicationRequest;
import com.patika.akbankservice.dto.response.ApplicationResponse;
import com.patika.akbankservice.entity.Application;
import com.patika.akbankservice.enums.BankApplicationStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationConverter {

    public Application toApplication(ApplicationRequest request) {
        Application application = new Application();
        application.setUserId(request.getUserId());
        application.setCreateDate(LocalDateTime.now());
        application.setApplicationStatus(BankApplicationStatus.RECEIVED);
        return application;
    }

    public ApplicationResponse toResponse(Application application) {
        return ApplicationResponse.builder()
                .userId(application.getUserId())
                .createDate(application.getCreateDate())
                .applicationStatus(application.getApplicationStatus())
                .build();
    }

    public List<ApplicationResponse> toResponseList(List<Application> applications) {
        List<ApplicationResponse> applicationResponses = new ArrayList<>();

        applications.forEach(application -> {
            applicationResponses.add(toResponse(application));
        });

        return applicationResponses;
    }
}
