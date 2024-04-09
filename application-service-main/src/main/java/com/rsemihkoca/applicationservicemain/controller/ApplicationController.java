package com.rsemihkoca.applicationservicemain.controller;

import com.rsemihkoca.applicationservicemain.dto.request.ApplicationRequest;
import com.rsemihkoca.applicationservicemain.dto.response.UserResponse;
import com.rsemihkoca.applicationservicemain.model.Application;
import com.rsemihkoca.applicationservicemain.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Application> createApplication(@RequestBody ApplicationRequest request) {
        return ResponseEntity.ok().body(applicationService.createApplication(request));
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Application>> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(applicationService.getByEmail(email));
    }


    @GetMapping("/")
    public ResponseEntity<List<Application>> getAll() {
        return ResponseEntity.ok().body(applicationService.getAll());
    }

    @GetMapping("/errorTest")
    public ResponseEntity<List<UserResponse>> testKafka() {
        throw new RuntimeException("test exception");
    }

}
