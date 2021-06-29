package com.dev.nc.jmanager.services;

import com.dev.nc.jmanager.configurations.JobSchedulerConfiguration;
import com.dev.nc.jmanager.models.Job;
import org.springframework.stereotype.Service;

/**
 * Service executor
 */
@Service
public class JobExecutorService {
    private final JobScheduler jobScheduler;

    public JobExecutorService(JobSchedulerConfiguration configuration) {
        jobScheduler = JobScheduler.getInstance(//wrap
                configuration.getPoolSize(), //wrap
                configuration.getQueueSize(), //wrap
                configuration.getSuspendTime());
    }

    public void execute(Job task) {
        jobScheduler.schedule(task);
    }

    public void stop() {
        jobScheduler.closeScheduler();
    }
}
