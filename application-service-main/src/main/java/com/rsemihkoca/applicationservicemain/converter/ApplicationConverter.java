//package com.rsemihkoca.applicationservicemain.converter;
//
//
//import com.rsemihkoca.applicationservicemain.dto.request.ApplicationRequest;
//import com.rsemihkoca.applicationservicemain.model.Application;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Component
//public class ApplicationConverter {
//
//    public Application toApplication(ApplicationRequest request, User user) {
//        Application application = new Application();
//        application.setApplicationStatus(ApplicationStatus.IN_PROGRESS);
//        application.setUser(user);
//        application.setLocalDateTime(LocalDateTime.now());
//        return application;
//    }
//}
