package com.dev.nc.jmanager;

import com.dev.nc.jmanager.models.AbstractJob;
import com.dev.nc.jmanager.models.JobPriority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Simple job definition with delay capability
 */
public class SimpleJob extends AbstractJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleJob.class);

    private Integer delay;
    private TimeUnit timeUnit;

    public SimpleJob(JobPriority priority) {
        super(priority);
    }

    public SimpleJob(Integer delay, TimeUnit timeUnit) {
        this.delay = delay;
        this.timeUnit = property(timeUnit, TimeUnit.SECONDS);
    }

    public SimpleJob(JobPriority priority, Integer delay, TimeUnit timeUnit) {
        super(priority);
        this.delay = delay;
        this.timeUnit = property(timeUnit, TimeUnit.SECONDS);
    }

    public SimpleJob() {
        // empty
    }

    @Override
    public void run() {
        LOGGER.info("EXECUTED: {}", getDescription());
    }

    @Override
    public ZonedDateTime getStartTime() {
        ZonedDateTime now = ZonedDateTime.now();
        if (delay != null) {
            now = now.plus(delay, timeUnit.toChronoUnit());
        }
        return now;
    }
}
