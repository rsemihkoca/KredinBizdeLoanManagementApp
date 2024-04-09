//package com.rsemihkoca.applicationservicemain.client.userservice;
//
//import com.rsemihkoca.applicationservicemain.client.BankServiceClient;
//import com.rsemihkoca.applicationservicemain.client.IUserServiceClient;
//import com.rsemihkoca.applicationservicemain.client.garanti.GarantiServiceClient;
//import com.rsemihkoca.applicationservicemain.dto.request.BankApplicationRequest;
//import com.rsemihkoca.applicationservicemain.dto.response.ApplicationResponse;
//import com.rsemihkoca.applicationservicemain.dto.response.UserResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//
//
//@Component
//public class UserClient implements IUserServiceClient {
//
//    @Autowired
//    private final UserServiceClient feignClient;
//
//    public UserClient() {
//        this.feignClient = null;
//    }
//
//    public UserClient(UserServiceClient feignClient) {
//        this.feignClient = feignClient;
//    }
//
//    @Override
//    public ResponseEntity<UserResponse> getByEmail(String email) {
//        if (feignClient == null) {
//            throw new UnsupportedOperationException("Feign client is not initialized");
//        }
//
//        return feignClient.getByEmail(email);
//    }
//}
//
