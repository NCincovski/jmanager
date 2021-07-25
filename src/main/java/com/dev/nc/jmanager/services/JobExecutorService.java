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
        jobScheduler = JobScheduler.getInstance(configuration.getPoolSize(),
                                                configuration.getQueueSize(),
                                                configuration.getSuspendTime());
    }

    public void execute(Job job) {
        jobScheduler.schedule(job);
    }

    public void stop() {
        jobScheduler.closeScheduler();
    }
}
