package com.ldyfortest.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

public class ExchangerTest {

    public static void main(String[] args) {
        java.util.concurrent.Exchanger<List<Integer>> exchanger = new java.util.concurrent.Exchanger<>();
        new Consumer(exchanger).start();
        new Producer(exchanger).start();
    }

}

class Producer extends Thread {
    List<Integer> list = new ArrayList<>();
    java.util.concurrent.Exchanger<List<Integer>> exchanger = null;
    public Producer(java.util.concurrent.Exchanger<List<Integer>> exchanger) {
        super();
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        Random rand = new Random();
        for(int i=0; i<10; i++) {
            list.clear();
            list.add(rand.nextInt(10000));
            list.add(rand.nextInt(10000));
            list.add(rand.nextInt(10000));
            list.add(rand.nextInt(10000));
            list.add(rand.nextInt(10000));
            list.forEach(num->{
                System.out.print(num+" ");
            });
            System.out.println();
            try {
                list = exchanger.exchange(list);
                SleepUtil.sleepSecond(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    List<Integer> list = new ArrayList<>();
    java.util.concurrent.Exchanger<List<Integer>> exchanger = null;
    public Consumer(java.util.concurrent.Exchanger<List<Integer>> exchanger) {
        super();
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            try {
                list = exchanger.exchange(list);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.print(list.get(0)+", ");
            System.out.print(list.get(1)+", ");
            System.out.print(list.get(2)+", ");
            System.out.print(list.get(3)+", ");
            System.out.println(list.get(4)+", ");
        }
    }
}
