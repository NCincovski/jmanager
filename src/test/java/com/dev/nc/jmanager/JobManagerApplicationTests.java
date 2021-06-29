package com.dev.nc.jmanager;

import com.dev.nc.jmanager.configurations.JobSchedulerConfiguration;
import com.dev.nc.jmanager.models.Job;
import com.dev.nc.jmanager.models.JobPriority;
import com.dev.nc.jmanager.services.JobExecutorService;
import com.dev.nc.jmanager.services.JobScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class JobManagerApplicationTests {
    @Mock
    JobSchedulerConfiguration configuration;
    @Mock
    Logger logger;
    @InjectMocks
    JobExecutorService service;

    @Test
    void contextLoads() {
    }

    @Test
    void simple() throws InterruptedException {
        Job jobHigh = new SimpleJob(JobPriority.HIGH);
        Job jobMedium = new SimpleJob(JobPriority.MEDIUM);
        Job jobLow = new SimpleJob(JobPriority.LOW);
        Job jobDefault = new SimpleJob();

        try (MockedStatic<LoggerFactory> factory = Mockito.mockStatic(LoggerFactory.class)) {
            factory.when(() -> LoggerFactory.getLogger(JobScheduler.class)).thenReturn(logger);
        }

        service.execute(jobDefault);
        service.execute(jobLow);
        service.execute(jobMedium);
        service.execute(jobHigh);

        TimeUnit.SECONDS.sleep(10);
        // service.stop();

        // Mockito.verify(logger).info(contains("HIGH"));
        // Mockito.verify(logger).info(contains("MEDIUM"));
        // Mockito.verify(logger).info(contains("LOW"));
        // Mockito.verify(logger).info(contains("DEFAULT"));
    }
}
