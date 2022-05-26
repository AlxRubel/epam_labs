package com.example.restservice.requestCounter;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class RequestCounterThread extends Thread
{
    private final Logger logger = LogManager.getLogManager().getLogger(String.valueOf(RequestCounterThread.class));

    private final String nameOfParentThread;

    public RequestCounterThread(String nameOfParentThread) {
        this.nameOfParentThread = nameOfParentThread;
    }

    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            System.out.println("Start: Available number of permits for " + threadName + " (parent " + nameOfParentThread + ") is: " + MySemaphore.semaphore.availablePermits());
            logger.info(threadName + " is waiting for resolution");
            MySemaphore.semaphore.acquire();
            System.out.println(threadName + " acquired permit");

            try {
                RequestCounter.increase();
            } finally {
                logger.info("Counter after increment " + RequestCounter.increase());
            }
        } catch (InterruptedException e) {
            logger.warning("Interrupted exception caught!");
        }
        System.out.println("Thread " + threadName + " (parent " + nameOfParentThread + ")" + " is terminating.");
    }
}
