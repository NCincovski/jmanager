package com.dev.nc.jmanager.remove;

import lombok.Getter;

import java.util.concurrent.FutureTask;

@Getter
public class CustomFutureTask<T> extends FutureTask<T> implements Comparable<CustomFutureTask> {
    Task task;

    public CustomFutureTask(CallableTask<T> callable) {
        super(callable);
        this.task = callable;
    }

    public CustomFutureTask(RunnableTask runnable) {
        super(runnable, null);
        this.task = runnable;
    }

    @Override
    public int compareTo(CustomFutureTask o) {
        return task.compareTo(o.getTask());
    }
}
