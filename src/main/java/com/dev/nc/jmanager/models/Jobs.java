package com.dev.nc.jmanager.models;

/**
 * Provides builder pattern for creating new {@link Job}
 */
public class Jobs {
    private static JobBuilder builder;

    private Jobs() {
        // empty
    }

    public static JobBuilder newBuilder() {
        if (builder == null) {
            builder = new JobBuilder();
        }
        return builder;
    }

    public static class JobBuilder {
        private String name;
        private String description;
        private JobPriority priority;

        public Job build(Runnable runnable) {
            AbstractJob job = new AbstractJob() {
                @Override
                public void run() {
                    runnable.run();
                }
            };
            job.setName(this.name);
            job.setDescription(this.description);
            job.setPriority(this.priority);
            return job;
        }

        public JobBuilder name(String name) {
            this.name = name;
            return this;
        }

        public JobBuilder description(String description) {
            this.description = description;
            return this;
        }

        public JobBuilder priority(JobPriority priority) {
            this.priority = priority;
            return this;
        }
    }
}
