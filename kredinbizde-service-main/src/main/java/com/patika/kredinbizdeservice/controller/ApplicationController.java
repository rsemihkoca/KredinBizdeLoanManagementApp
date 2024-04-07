package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.dto.request.ApplicationRequest;
import com.patika.kredinbizdeservice.model.Application;
import com.patika.kredinbizdeservice.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Application> createApplication(@RequestBody ApplicationRequest request) {
        return ResponseEntity.ok().body(applicationService.createApplication(request));
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Application>> getAll(@PathVariable String email) {
        return ResponseEntity.ok().body(applicationService.getAll(email));
    }

    @GetMapping
    public ResponseEntity<List<Application>> testKafka() {
        throw new RuntimeException("test exception");
    }

}
