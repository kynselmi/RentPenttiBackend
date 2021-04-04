package com.kingofnone.rentpentti.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class ScheduledTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public void importPayments() {

    }

    @Override
    public void run() {
        importPayments();
    }
}
