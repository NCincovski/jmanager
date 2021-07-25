package com.dev.nc.jmanager;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;

public class TestLogAppender extends AppenderBase<ILoggingEvent> {
    private final ArrayList<ILoggingEvent> loggingEvents = new ArrayList<>();

    @Override
    protected void append(ILoggingEvent eventObject) {
        loggingEvents.add(eventObject);
    }

    public ILoggingEvent getLastLoggedEvent() {
        return getLastLoggedEvents(1).get(0);
    }

    public List<ILoggingEvent> getLastLoggedEvents(int number) {
        if (loggingEvents.size() < number) {
            return null;
        }
        return loggingEvents.subList(loggingEvents.size() - number, loggingEvents.size());
    }

    public ArrayList<ILoggingEvent> getAllLoggingEvents() {
        return loggingEvents;
    }
}

