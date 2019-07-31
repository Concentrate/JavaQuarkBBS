package com.ldyfortest.concurrent;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by liudeyu on 2019/7/29.
 */
public class CustomLockImp {


    /**
     * 每次至多只有两个线程能同时进入
     */
    private static class TwinLock implements Lock {


        private Sync sync = new Sync(2);

        @Override
        public void lock() {
            sync.acquireShared(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, @NotNull TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {
            sync.releaseShared(1);

        }

        @NotNull
        @Override
        public Condition newCondition() {
            return null;
        }

        private static class Sync extends AbstractQueuedLongSynchronizer {
            int count;

            public Sync(int count) {
                this.count = count;
                if (count <= 0) {
                    throw new IllegalArgumentException("count must be great than zero");
                }
                setState(count);
            }

            @Override
            protected long tryAcquireShared(long arg) {
                for (; ; ) {
                    long reduce = getState() - arg;
                    if (reduce < 0 || compareAndSetState(getState(), reduce)) {
                        return reduce;
                    }
                }
            }


            @Override
            protected boolean tryReleaseShared(long arg) {
                for (; ; ) {
                    long nowState = getState() + arg;
                    if (compareAndSetState(getState(), nowState)) {
                        return true;
                    }
                }
            }

        }

    }


    private static class ARun implements Runnable {

        Lock lock;

        public ARun(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println("current thread is " + Thread.currentThread());
                SleepUtil.sleepSecond(3);
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] argv) {

        TwinLock twinLock = new TwinLock();

        int count = 10;
        for (int i = 0; i < count; i++) {
            new Thread(new ARun(twinLock), "thread:" + i).start();
        }
        
       SleepUtil.sleepSecond(100);
        
    }

}
