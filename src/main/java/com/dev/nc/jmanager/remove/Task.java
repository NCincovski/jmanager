package com.dev.nc.jmanager.remove;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Task implements Comparable<Task> {
    private Job job;

    public Task(Job job) {
        this.job = job;
    }

    @Override
    public int compareTo(Task o) {
        return this.job.compareTo(o.getJob());
    }
}
