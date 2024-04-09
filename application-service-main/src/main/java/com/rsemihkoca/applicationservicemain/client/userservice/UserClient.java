package com.rsemihkoca.applicationservicemain.client.userservice;

import com.rsemihkoca.applicationservicemain.client.akbank.AkbankServiceClient;
import com.rsemihkoca.applicationservicemain.dto.request.BankApplicationRequest;
import com.rsemihkoca.applicationservicemain.dto.response.ApplicationResponse;
import com.rsemihkoca.applicationservicemain.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserClient implements UserServiceClient{

    private final UserServiceClient feignClient;

    public UserClient(UserServiceClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public ResponseEntity<UserResponse> getByEmail(String email) {
        return null;
    }
}

