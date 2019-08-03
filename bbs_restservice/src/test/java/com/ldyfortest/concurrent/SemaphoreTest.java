package com.ldyfortest.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreTest implements Runnable {

    private static Semaphore semaphore = new Semaphore(10);
    private static int threadCount = 30;
    private static Executor executor = Executors.newFixedThreadPool(threadCount);

    private static AtomicInteger atomicInteger = new AtomicInteger(0);


    public static void main(String[] args) {

        for (int i = 0; i < threadCount; i++) {
            executor.execute(new SemaphoreTest());
        }

    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(atomicInteger.getAndIncrement());
            SleepUtil.sleepSecond(5);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
