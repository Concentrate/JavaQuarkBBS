package com.ldyfortest.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liudeyu on 2019/7/30.
 */
public class LockCondictionArray<T> {

    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition notFull = reentrantLock.newCondition();
    private Condition notEmpty = reentrantLock.newCondition();
    int addIndex = 0;
    int removeIndex = 0;
    int currentCount = 0;
    private Object[] dataArray;

    public LockCondictionArray(int count) {
        dataArray = new Object[count];
    }


    public void add(T t) throws InterruptedException {
        reentrantLock.lock();
        try {
            while (currentCount == dataArray.length) {
                notFull.await();
            }
            if (addIndex >= dataArray.length) {
                addIndex = 0;
            }
            dataArray[addIndex++] = t;
            currentCount++;
            notEmpty.signal();

        } finally {
            reentrantLock.unlock();
        }
    }


    public T removeTop() throws InterruptedException {
        reentrantLock.lock();
        try {
            while (currentCount <= 0) {
                notEmpty.await();
            }

            if (removeIndex >= dataArray.length) {
                removeIndex = 0;
            }
            T t = (T) dataArray[removeIndex++];
            currentCount--;
            notFull.signal();
            return t;
        } finally {
            reentrantLock.unlock();

        }
    }


    public static void main(String[] argv) {
        LockCondictionArray<Integer> array = new LockCondictionArray<>(5);
        Thread tAdd = new Thread(() -> {
            int index = -1;
            while (true) {
                try {
                    array.add(++index);
                    System.out.println("add :"+index);
                    SleepUtil.sleepSecond(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread tRemove = new Thread(() -> {
            while (true) {
                try {
                    System.out.println(array.removeTop());
                    SleepUtil.sleepSecond(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        tAdd.start();
        tRemove.start();

    }

}
