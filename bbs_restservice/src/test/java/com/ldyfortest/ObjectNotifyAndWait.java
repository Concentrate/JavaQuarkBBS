package com.ldyfortest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liudeyu on 2019/7/26.
 */
public class ObjectNotifyAndWait {

    static Object lock = new Object();
    private static boolean tag = true;
    static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    static class WaitRunner implements Runnable {


        private String logTag = this.getClass().getSimpleName() + ": ";

        @Override
        public void run() {
            synchronized (lock) {
                while (tag) {
                    System.out.println(logTag + dateFormat.format(new Date()));
                    try {
                        lock.wait();
                        System.out.println(logTag + " wait back and time is " + dateFormat.format(new Date()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class NotiRunner implements Runnable {
        private String logTag = this.getClass().getSimpleName() + ": ";

        @Override
        public void run() {

            synchronized (lock) {
                System.out.println(logTag + " time is " + dateFormat.format(new Date()));
                tag = false;
//                lock.notifyAll();
                System.out.println(logTag + "continue run until release the lock");
            }

            SleepUtil.sleepSecond(1);

            synchronized (lock) {
                System.out.println(logTag + "get the lock again " + "and the time is " + dateFormat.format(new Date()));
            }
        }
    }


    public static void main(String[] argv) {
        Thread waitT = new Thread(new WaitRunner(), "wait");
        Thread notiT = new Thread(new NotiRunner(), "noti");
        waitT.start();
        SleepUtil.sleepSecond(3);
        notiT.start();
        SleepUtil.sleepSecond(10);
     //   System.exit(0); // test process exit

    }
}
