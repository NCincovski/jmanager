package com.dev.nc.jmanager.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.nonNull;

/**
 * Provides builder pattern for creating new {@link Job}
 */
public class Jobs {
    private static final Logger LOGGER = LoggerFactory.getLogger(Jobs.class);

    private Jobs() {
        // empty
    }

    public static JobBuilder newJob() {
        return new JobBuilder();
    }

    public static class JobBuilder {
        private String name;
        private String description;
        private JobPriority priority;
        private Integer delay;
        private TimeUnit timeUnit;

        public Job build(Runnable runnable) {
            AbstractJob job = new AbstractJob() {
                @Override
                public void run() {
                    if (nonNull(runnable)) {
                        runnable.run();
                    } else {
                        LOGGER.info("EXECUTED: {}", getDescription());
                    }
                }

                @Override
                public ZonedDateTime getStartTime() {
                    ZonedDateTime startTime = super.getStartTime();
                    if (nonNull(delay)) {
                        startTime = startTime.plus(delay, timeUnit.toChronoUnit());
                    }
                    return startTime;
                }
            };
            job.setName(this.name);
            job.setDescription(this.description);
            job.setPriority(this.priority);
            return job;
        }

        public JobBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public JobBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public JobBuilder withPriority(JobPriority priority) {
            this.priority = priority;
            return this;
        }

        public JobBuilder withDelay(Integer delay, TimeUnit timeUnit) {
            this.delay = delay;
            this.timeUnit = Objects.isNull(timeUnit) ? TimeUnit.SECONDS : timeUnit;
            return this;
        }
    }
}
