package com.ldyfortest;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by liudeyu on 2019/7/26.
 */
public class ProduceAndConsumerModel {

    static java.lang.Object lock = new java.lang.Object();
    static Queue<Integer> factory = new LinkedList<>();
    static int FULL_SIZE = 10;
    static int index = 0;

    static class ActionSleep {
        protected int actionSleepTime;

        public ActionSleep(int actionSleepTime) {
            this.actionSleepTime = actionSleepTime;
        }
    }

    static class Factory extends ActionSleep implements Runnable {

        private String tag = this.getClass().getSimpleName() + ": ";

        public Factory(int actionSleepTime) {
            super(actionSleepTime);
        }

        @Override
        public void run() {


            while (true) {
                synchronized (lock) {
//                    System.out.println("enter producer");
                    if (factory.size() >= FULL_SIZE) {
                        System.out.println(tag + " factory is full and wait ");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int value = index++;
                    factory.offer(value);
                    System.out.println(tag + "start produce " + value);
                }
                SleepUtil.sleepSecond(actionSleepTime);
            }

        }
    }

    static class Consumer extends ActionSleep implements Runnable {
        private String tag = this.getClass().getSimpleName() + ": ";

        public Consumer(int actionSleepTime) {
            super(actionSleepTime);
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
//                    System.out.println("enter consumer");
                    if (factory.isEmpty()) {
                        System.out.println(tag + " factory is empty and wait");
                        lock.notifyAll();
                    } else {
                        System.out.println(tag + " coumser consume " + factory.poll());
                    }
                }
                SleepUtil.sleepSecond(actionSleepTime);
            }

        }
    }


    public static void main(String[] argv) {

        Factory factory = new Factory(1);
        Consumer consumer = new Consumer(2);

        Thread t1 = new Thread(factory, factory.getClass().getSimpleName());
        Thread t2 = new Thread(consumer, consumer.getClass().getSimpleName());
        t1.start();
//        com.ldyfortest.SleepUtil.sleepSecond(3);
        t2.start();
//        System.out.println("start test");

    }


}
