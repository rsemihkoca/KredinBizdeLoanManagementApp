package com.rsemihkoca.userservicemain.controller;


import com.rsemihkoca.userservicemain.exceptions.dto.request.CreateUserRequest;
import com.rsemihkoca.userservicemain.exceptions.dto.response.GenericResponse;
import com.rsemihkoca.userservicemain.exceptions.dto.response.UserResponse;
import com.rsemihkoca.userservicemain.model.User;
import com.rsemihkoca.userservicemain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<GenericResponse<UserResponse>> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(GenericResponse.success(userService.createUser(request)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<GenericResponse<UserResponse>> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(GenericResponse.success(userService.getByEmail(email)));
    }

    @GetMapping("/")
    public ResponseEntity<GenericResponse<List<UserResponse>>> getAll() {
        return ResponseEntity.ok(GenericResponse.success(userService.getAll()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GenericResponse<UserResponse>> getById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(GenericResponse.success(userService.getById(userId)));
    }

    @GetMapping("/errorTest")
    public ResponseEntity<GenericResponse<List<UserResponse>>> testKafka() {
        throw new RuntimeException("test exception");
    }

}
