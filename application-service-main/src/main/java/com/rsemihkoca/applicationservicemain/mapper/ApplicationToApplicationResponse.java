package com.rsemihkoca.applicationservicemain.mapper;

import com.rsemihkoca.applicationservicemain.dto.response.ApplicationResponse;
import com.rsemihkoca.applicationservicemain.model.Application;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class ApplicationToApplicationResponse implements Converter<Application, ApplicationResponse> {


    @Override
    public ApplicationResponse convert(MappingContext<Application, ApplicationResponse> mappingContext) {
        Application application = mappingContext.getSource();
        return ApplicationResponse.builder()
                .userEmail(application.getUserEmail())
                .applicationStatus(application.getApplicationStatus().toString())
                .applicationDate(application.getApplicationDate())
                .build();
    }
}
