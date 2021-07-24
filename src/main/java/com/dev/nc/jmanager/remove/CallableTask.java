package com.dev.nc.jmanager.remove;

import java.util.concurrent.Callable;

public abstract class CallableTask<T> extends Task implements Callable<T> {

    public CallableTask(TaskDetails taskDetails) {
        super(taskDetails);
    }

}
