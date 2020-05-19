package com.example.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Slf4j
@Component
public class TaskErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable throwable) {
        log.error("config error");
    }
}
