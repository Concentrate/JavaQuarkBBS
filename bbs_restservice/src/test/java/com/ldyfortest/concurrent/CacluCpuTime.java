package com.ldyfortest.concurrent;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by liudeyu on 2019/7/31.
 */
public class CacluCpuTime {

    interface Process<T> {
        T process();

        default void printResult() {
        }
    }


    public static void cacluTime(Process process, String tagName) {
        long start = System.currentTimeMillis();
        process.process();
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println(tagName + ": " + (end - start) + " ms and the result is " + process.process());
        process.printResult();
    }


}
