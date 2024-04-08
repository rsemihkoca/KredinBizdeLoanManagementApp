package com.rsemihkoca.userservicemain.controller;


import com.rsemihkoca.userservicemain.exceptions.dto.request.CreateUserRequest;
import com.rsemihkoca.userservicemain.exceptions.dto.response.ApiResponse;
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
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getById(userId));
    }

    @GetMapping("/errorTest")
    public ResponseEntity<List<UserResponse>> testKafka() {
        throw new RuntimeException("test exception");
    }

}
