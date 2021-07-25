package com.dev.nc.jmanager.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * All concrete implementations should inherit this class. It provides default implementation for some
 * of the methods defined in {@link Job} interface
 */
public abstract class AbstractJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractJob.class);

    private final String id;
    private String name;
    private String description;
    private JobPriority priority;
    private JobState state;

    public AbstractJob() {
        id = UUID.randomUUID().toString();
        this.state = JobState.CREATED;
    }

    public AbstractJob(JobPriority priority) {
        this();
        this.priority = priority;
    }

    public AbstractJob(String name, String description, JobPriority priority) {
        this(priority);
        this.name = name;
        this.description = description;
    }

    /**
     * Default definition for the methods in the interface {@link Job}
     */
    @Override
    public String getName() {
        return property(name, "J-" + id);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return property(description, getName() + "-" + getPriority());
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public JobPriority getPriority() {
        return property(priority, JobPriority.DEFAULT);
    }

    public void setPriority(JobPriority priority) {
        this.priority = priority;
    }

    @Override
    public JobState getState() {
        return state;
    }

    @Override
    public void setState(JobState state) {
        LOGGER.info("{} --> {}", getName(), state);
        this.state = state;
    }

    @Override
    public ZonedDateTime getStartTime() {
        return ZonedDateTime.now();
    }

    protected <T> T property(T t, T r) {
        return t != null ? t : r;
    }
}
