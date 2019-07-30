package com.ldyfortest;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by liudeyu on 2019/7/30.
 */
public class ParkTest {
    public static void main(String[]argv) {
        Runnable ru = ()->{
            while (true) {
                System.out.println("hello,run");
//                SleepUtil.sleepSecond(3);
                LockSupport.park();
            }

        };

        Thread thread =new Thread(ru);
        thread.start();
        while (true) {
            LockSupport.unpark(thread);
            SleepUtil.sleepSecond(3);
        }
    }
}
