package com.dev.nc.jmanager.models;

import java.time.ZonedDateTime;

public interface Job extends Comparable<Job>, Stateful<JobState>, Runnable {
    String getName();

    String getDescription();

    JobPriority getPriority();

    ZonedDateTime getStartTime();

    @Override
    default int compareTo(Job o) {
        return getPriority().compareTo(o.getPriority());
    }
}
