package com.example.restservice.requestCounter;

import java.util.concurrent.Semaphore;

public class MySemaphore {
    public final static Semaphore semaphore = new Semaphore(1, true);
}
