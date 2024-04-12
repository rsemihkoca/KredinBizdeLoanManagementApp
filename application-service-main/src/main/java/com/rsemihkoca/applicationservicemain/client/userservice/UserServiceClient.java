package com.rsemihkoca.applicationservicemain.client.userservice;

//import com.rsemihkoca.applicationservicemain.client.IUserServiceClient;
import com.rsemihkoca.applicationservicemain.dto.response.GenericResponse;
import com.rsemihkoca.applicationservicemain.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = "user-service-main", url = "http://localhost:8081")
@FeignClient(value = "user-service-main")
public interface UserServiceClient {

    @GetMapping("/api/v1/user/email/{email}")
    ResponseEntity<GenericResponse<UserResponse>> getByEmail(@PathVariable String email);
}