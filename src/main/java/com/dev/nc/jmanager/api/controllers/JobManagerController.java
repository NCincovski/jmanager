package com.dev.nc.jmanager.api.controllers;

import com.dev.nc.jmanager.SimpleJob;
import com.dev.nc.jmanager.api.payload.JobRequest;
import com.dev.nc.jmanager.models.Job;
import com.dev.nc.jmanager.services.JobExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job")
public class JobManagerController {
    @Autowired
    JobExecutorService service;

    @PostMapping("/new")
    public ResponseEntity<Job> postNew(@RequestBody JobRequest request) {
        SimpleJob job = new SimpleJob(request.getPriority(), request.getDelay(), request.getTimeUnit());
        service.execute(job);
        return ResponseEntity.ok(job);
    }
}
