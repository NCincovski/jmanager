package com.dev.nc.jmanager.services;

import com.dev.nc.jmanager.models.Job;
import com.dev.nc.jmanager.models.JobState;
import com.dev.nc.jmanager.utilities.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.*;

import static com.dev.nc.jmanager.models.JobState.FAILED;
import static com.dev.nc.jmanager.models.JobState.SUCCESS;

/**
 * Singleton component responsible for scheduling and execution of the jobs while
 * setting each one in the appropriate state based on completion
 */
public class JobScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobScheduler.class);

    private static JobScheduler INSTANCE;
    private final ExecutorService executor;
    private final ExecutorService queueListenerExecutor = Executors.newSingleThreadExecutor();
    private final PriorityBlockingQueue<Job> priorityQueue;

    public static JobScheduler getInstance(int poolSize, int queueSize, int suspendTime) {
        if (INSTANCE == null) {
            INSTANCE = new JobScheduler(poolSize, queueSize, suspendTime);
        }
        return INSTANCE;
    }

    private JobScheduler(int poolSize, int queueSize, int suspendTime) {
        executor = Executors.newFixedThreadPool(poolSize); // TODO: define custom executor
        priorityQueue = new PriorityBlockingQueue<>(queueSize);
        queueListenerExecutor.execute(() -> {
            Job current = null;
            while (true) { //!Thread.currentThread().isInterrupted()
                try {
                    if (priorityQueue.size() > 0) {
                        current = priorityQueue.take();
                        current.setState(JobState.RUNNING);
                        long delay = calculateDelay(current.getStartTime());
                        LOGGER.info("SCHEDULING: {} at: {}",
                                    current.getDescription(),
                                    TimeUtil.formatZonedDateTime(current.getStartTime()));
                        final Job job = current;
                        CompletableFuture.supplyAsync(() -> job,
                                                      CompletableFuture.delayedExecutor(delay,
                                                                                        TimeUnit.SECONDS,
                                                                                        executor))
                                         .thenAccept((j) -> j.setState(SUCCESS));
                    } else {
                        sleep(suspendTime);
                    }
                } catch (InterruptedException e) {
                    // exception needs special handling
                    if (current != null) {
                        current.setState(FAILED);
                    }
                    LOGGER.error("Job scheduling interrupted: {}", e.getMessage());
                    break;
                }
            }
        });
    }

    private long calculateDelay(ZonedDateTime scheduledTime) {
        long delay = 0;
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        if (scheduledTime.isAfter(now)) {
            Duration duration = Duration.between(now, scheduledTime);
            delay = duration.getSeconds();
        }

        return delay;
    }

    private void sleep(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000L);
    }

    public void schedule(Job job) {
        job.setState(JobState.QUEUED);
        priorityQueue.add(job);
    }

    private void close(ExecutorService scheduler) {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(1, TimeUnit.MINUTES)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }

    public void closeScheduler() {
        close(executor);
        close(queueListenerExecutor);
    }
}
