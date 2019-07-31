package com.ldyfortest.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by liudeyu on 2019/7/24.
 */


public class ThreadTest {


    private static class BackgroundRunner implements Runnable {


        public static void main(String[] argv) {

            Thread thread = new Thread(new BackgroundRunner());
            thread.setDaemon(true);
            thread.start();
            System.out.println("main is end");
        }


        @Override
        public void run() {
            try {
                Thread.sleep(3 * 1000);
                System.out.println("background thread say something");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static class BusyRunner implements Runnable {

        @Override
        public void run() {
            int index = 0;
            while (true) {
                index++;
                if (index % 10000000 == 0) {
                    System.out.println("thread current is interrupt " + Thread.currentThread().isInterrupted());
                }
//                com.ldyfortest.concurrent.SleepUtil.sleepSecond(1);
            }
        }
    }

    private static class SleepRunner implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ThreadTestMain {
        public static void main(String[] argv) {

            Thread busy = new Thread(new BusyRunner());
            Thread sleep = new Thread(new SleepRunner());
            busy.start();
            sleep.start();
            SleepUtil.sleepSecond(3);
            busy.interrupt();
            sleep.interrupt();
            System.out.println("busy thread is interredp" + busy.isInterrupted());
            System.out.println("sleep thread is interrepte" + sleep.isInterrupted());
            SleepUtil.sleepSecond(10);


        }
    }

    @Test
    public void testBackgroundThread() {

        BackgroundRunner.main(null);

    }


}
