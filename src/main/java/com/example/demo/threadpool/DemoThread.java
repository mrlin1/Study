package com.example.demo.threadpool;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoThread implements Runnable, Callable<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoThread.class);

    private String userId;

    @Override
    public void run() {
        LOGGER.info("demoThread. userId:[{}]", userId);
    }

    @Override
    public String call() throws Exception {
        LOGGER.info("demoThread. userId:[{}]", userId);
        return userId;
    }

}
