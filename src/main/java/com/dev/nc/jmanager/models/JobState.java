package com.dev.nc.jmanager.models;

/**
 * State enumeration: <br/>
 * - QUEUED means "added to the queue"<br/>
 * - RUNNING means "scheduled for execution"<br/>
 * - SUCCESS means "execution successful"<br/>
 * - FAILED means "execution failed"<br/>
 */
public enum JobState {
    CREATED, QUEUED, RUNNING, SUCCESS, FAILED;
}
