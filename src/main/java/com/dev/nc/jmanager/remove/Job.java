package com.dev.nc.jmanager.remove;

import com.dev.nc.jmanager.models.JobPriority;
import com.dev.nc.jmanager.models.JobState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Job implements Comparable<Job> {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private JobPriority priority = JobPriority.DEFAULT;
    private JobState state;

    public Job(String name, String description, JobPriority priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    @Override
    public int compareTo(Job o) {
        return this.priority.compareTo(o.priority);
    }
}
