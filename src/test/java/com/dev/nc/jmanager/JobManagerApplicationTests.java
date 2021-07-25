package com.dev.nc.jmanager;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.dev.nc.jmanager.models.Job;
import com.dev.nc.jmanager.models.Jobs;
import com.dev.nc.jmanager.services.JobExecutorService;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.dev.nc.jmanager.models.JobPriority.*;
import static com.dev.nc.jmanager.models.Jobs.newJob;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JobManagerApplicationTests {
    @Autowired
    JobExecutorService service;

    @Test
    void simple() throws InterruptedException {
        TestLogAppender testLogAppender = new TestLogAppender();
        Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.addAppender(testLogAppender);
        testLogAppender.start();

        Job jHigh = newJob().withPriority(HIGH).withDelay(2, SECONDS).build(null);
        Job jMedium = newJob().withPriority(MEDIUM).withDelay(1, SECONDS).build(null);
        Job jLow = newJob().withPriority(LOW).build(null);
        Job jDefault = newJob().build(null);

        service.execute(jDefault);
        service.execute(jLow);
        service.execute(jMedium);
        service.execute(jHigh);

        SECONDS.sleep(5);
        // service.stop();

        ILoggingEvent lastLoggedEvent = testLogAppender.getLastLoggedEvent();
        assertNotNull(lastLoggedEvent);
        assertTrue(lastLoggedEvent.getFormattedMessage().contains(jHigh.getName()));

        final List<ILoggingEvent> lastLoggingEvents = testLogAppender.getLastLoggedEvents(4);
        lastLoggingEvents.forEach(e -> assertTrue(e.getFormattedMessage().contains("SUCCESS")));
    }
}
