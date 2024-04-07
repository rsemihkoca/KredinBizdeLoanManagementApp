package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.model.Application;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope(value = "singleton")
public class ApplicationRepository {

    private List<Application> applicationList = new ArrayList<>();

    public Application save(Application application) {
        applicationList.add(application);
        return application;
    }

    public List<Application> getAll(String email) {

        return applicationList.stream()
                .filter(application -> application.getUser().getEmail().equals(email))
                .toList();
    }
}
