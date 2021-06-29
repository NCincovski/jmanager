package com.dev.nc.jmanager.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuration component containing properties for the pool size and queue size.
 * Properties are read from the application.properties. If not configured, defaults are provided.
 */
@Component
@Getter
@Setter
public class JobSchedulerConfiguration {
    @Value("${scheduler.poolSize:2}")
    private int poolSize;
    @Value("${scheduler.queueSize:10}")
    private int queueSize;
    @Value("${scheduler.suspendTime:0}")
    private int suspendTime;
}
