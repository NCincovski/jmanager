package com.dev.nc.jmanager.api.controllers;

import com.dev.nc.jmanager.api.payload.JobRequest;
import com.dev.nc.jmanager.models.Job;
import com.dev.nc.jmanager.models.Jobs;
import com.dev.nc.jmanager.services.JobExecutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job")
public class JobManagerController {
    final JobExecutorService service;

    public JobManagerController(JobExecutorService service) {
        this.service = service;
    }

    @PostMapping("/new")
    public ResponseEntity<Job> postNew(@RequestBody JobRequest request) {
        Job job = Jobs.newJob()
                      .withName(request.getName())
                      .withDescription(request.getDescription())
                      .withPriority(request.getPriority())
                      .withDelay(request.getDelay(), request.getTimeUnit())
                      .build(request.getActivity());
        service.execute(job);
        return ResponseEntity.ok(job);
    }
}
