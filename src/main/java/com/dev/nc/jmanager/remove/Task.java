package com.dev.nc.jmanager.remove;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Task implements Comparable<Task> {
    private TaskDetails taskDetails;

    public Task(TaskDetails taskDetails) {
        this.taskDetails = taskDetails;
    }

    @Override
    public int compareTo(Task o) {
        return this.taskDetails.compareTo(o.getTaskDetails());
    }
}
