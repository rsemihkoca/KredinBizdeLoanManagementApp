package com.rsemihkoca.applicationservicemain.client.userservice;

//import com.rsemihkoca.applicationservicemain.client.IUserServiceClient;
import com.rsemihkoca.applicationservicemain.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service", url = "http://localhost:8081/api/v1/user/email")
public interface UserServiceClient {

    @GetMapping("/{email}")
    ResponseEntity<UserResponse> getByEmail(@PathVariable String email);
}