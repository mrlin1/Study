package com.example.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskCenter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskCenter.class);

    @Scheduled(cron = "0/5 * * * * ?")
    public void task1() throws InterruptedException {
        LOGGER.info("this is task1");
        Thread.sleep(10000);
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void task2() {
        LOGGER.info("this is task2");
    }

}
