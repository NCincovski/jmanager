package com.dev.nc.jmanager.api.payload;

import com.dev.nc.jmanager.models.JobPriority;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

/**
 * Entity request/response used as payload for accepting job definition
 */
@Getter
@Setter
public class JobRequest {
    private String name;
    private String description;
    private JobPriority priority;
    private Integer delay;
    private TimeUnit timeUnit;
    private Runnable activity;
}
