package com.rsemihkoca.applicationservicemain.aspect.implementation;

import com.rsemihkoca.applicationservicemain.client.userservice.UserServiceClient;
import com.rsemihkoca.applicationservicemain.dto.request.interfaces.HasEmail;
import com.rsemihkoca.applicationservicemain.dto.response.GenericResponse;
import com.rsemihkoca.applicationservicemain.dto.response.UserResponse;
import com.rsemihkoca.applicationservicemain.model.ApplicationPipeline;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
@Order(value = 1)
public class CheckUserExistenceImpl {

    private final UserServiceClient userServiceClient;
    private final ApplicationPipeline applicationPipeline;


    @Before("@annotation(com.rsemihkoca.applicationservicemain.aspect.signature.CheckUserExistence) && execution(* *(..))")
    public void checkUserExistence(JoinPoint joinPoint) {
            Object[] args = joinPoint.getArgs();
            // search for email in the request arguments
            for (Object arg : args) {

                if (arg instanceof HasEmail request) {
                    String email = request.getEmail();
                    findUserByEmail(email);
                } else if (arg instanceof String email) {
                    findUserByEmail(email);
                } else {
                    throw new RuntimeException("Email not found in the request");
                }
        }
    }

    private void findUserByEmail(String email) {
        ResponseEntity<GenericResponse<UserResponse>> user = userServiceClient.getByEmail(email);
        if (user.getBody().getData().getEmail() != null) {
            log.info("User found");
            applicationPipeline.setCurrentUserEmail(email);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}

