package com.dev.nc.jmanager.models;

public interface Stateful<S> {
    S getState();

    void setState(S s);
}
