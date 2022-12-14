package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * Executes the task every 5000 milliseconds
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        LOGGER.info("The time is now {}", dataFormat.format(new Date()));
    }
}
