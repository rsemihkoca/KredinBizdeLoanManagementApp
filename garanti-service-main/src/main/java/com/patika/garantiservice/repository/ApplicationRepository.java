package com.patika.garantiservice.repository;

import com.patika.garantiservice.entity.Application;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ApplicationRepository {

    private List<Application> applicationList = new ArrayList<>();

    public Application save(Application application) {
        applicationList.add(application);
        return application;
    }

    public List<Application> getAll() {
        return applicationList;
    }
}
