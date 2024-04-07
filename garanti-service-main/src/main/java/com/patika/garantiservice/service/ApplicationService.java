package com.patika.garantiservice.service;

import com.patika.garantiservice.converter.ApplicationConverter;
import com.patika.garantiservice.dto.request.ApplicationRequest;
import com.patika.garantiservice.dto.response.ApplicationResponse;
import com.patika.garantiservice.entity.Application;
import com.patika.garantiservice.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final ApplicationConverter applicationConverter;

    public ApplicationService(ApplicationRepository applicationRepository, ApplicationConverter applicationConverter) {
        this.applicationRepository = applicationRepository;
        this.applicationConverter = applicationConverter;
    }

    public ApplicationResponse createApplication(ApplicationRequest request) {

        Application application = applicationConverter.toApplication(request);
        ApplicationResponse response = applicationConverter.toResponse(applicationRepository.save(application));
        return response;
    }


    public List<ApplicationResponse> getAll() {
        List<Application> applications = applicationRepository.getAll();

        return applicationConverter.toResponseList(applications);
    }
}
