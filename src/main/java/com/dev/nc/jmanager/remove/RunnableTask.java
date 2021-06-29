package com.dev.nc.jmanager.remove;

import lombok.Getter;

@Getter
public abstract class RunnableTask extends Task implements Runnable {

    public RunnableTask(Job job) {
        super(job);
    }

}
