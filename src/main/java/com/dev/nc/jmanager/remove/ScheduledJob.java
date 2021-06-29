package com.dev.nc.jmanager.remove;

import com.dev.nc.jmanager.models.JobPriority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ScheduledJob extends Job {
    private ZonedDateTime startTime;

    public ScheduledJob(String name, String description, JobPriority priority, ZonedDateTime startTime) {
        super(name, description, priority);
        this.startTime = startTime;
    }
}
