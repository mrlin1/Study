package com.example.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TaskCenter {

    @Scheduled(cron = "0/5 * * * * ?")
    public void task1() throws Exception {
        for (int i = 0; i < 10; i++) {
            log.info(String.valueOf(i));
            if (i == 5) {
                throw new Exception("error");
            }
        }
    }

//    @Scheduled(cron = "0/1 * * * * ?")
//    public void task2() {
//        log.info("this is task2");
//    }

}
