package com.ldyfortest.concurrent;

import com.google.common.collect.Collections2;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.concurrent.Task;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by liudeyu on 2019/7/31.
 */
public class ForkJoinTaskTest {

    public static final long CAL_END = 1000000000;

    public static final int SORT_NUM_COUNT = 100000;

    static class ForkNumAddTask extends RecursiveTask<Long> {

        private long start = 0;
        private long end = 0;
        static long SPLIT_GAP = CAL_END / 8;

        public ForkNumAddTask(long start, long end) {
            this.start = start;
            this.end = end;
            if (start >= end) {
                throw new IllegalArgumentException("para wrong");
            }
        }

        @Override
        protected Long compute() {
            long tmp = 0;
            if (end - start < SPLIT_GAP) {
                for (long i = start; i < end; i++) {
                    tmp += i;
                }
            } else {
                long mid = start + (end - start) / 2;
                ForkNumAddTask forkNumAddTask = new ForkNumAddTask(start, mid);
                ForkNumAddTask endTask = new ForkNumAddTask(mid, end);
                forkNumAddTask.fork();
                endTask.fork();
                tmp = forkNumAddTask.join() + endTask.join();
            }
            return tmp;
        }

    }

    static class MutipleThreadTaskProcesser implements CacluCpuTime.Process<Long> {

        @Override
        public Long process() {
            ForkNumAddTask forkNumAddTask = new ForkNumAddTask(0, CAL_END);
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            Future<Long> result = forkJoinPool.submit(forkNumAddTask);
            long finalRes = 0;
            try {
                finalRes = result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return finalRes;
        }
    }

    public static void printResult(List<Integer> randomList) {
        int printMax = 100;
        for (int i = 0; i < randomList.size(); i++) {
            System.out.print(randomList.get(i) + " ");
            if (i > printMax) {
                return;
            }
        }
        System.out.println();
    }

    static class SingleThreadTaskProcessor implements CacluCpuTime.Process<Long> {

        @Override
        public Long process() {
            long res = 0;
            for (int i = 0; i < CAL_END; i++) {
                res += i;
            }
            return res;

        }
    }


    static class MultiSortJoinTask extends RecursiveTask<List<Integer>> {

        private List<Integer> randomList;
        static int DEAL_GAP = 0;

        public MultiSortJoinTask(List<Integer> randomList) {
            this.randomList = randomList;
            if (randomList == null || randomList.size() == 0) {
                throw new IllegalArgumentException("size cann't be zero");
            }
            DEAL_GAP = randomList.size() / 4;
//            System.out.println("random size is "+randomList.size());
        }


        /**
         * not work,I don't know why
         */
        @Override
        protected List<Integer> compute() {
            List<Integer> result = new ArrayList<>();
            if (randomList.size() < DEAL_GAP) {
                randomList.sort(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });
                result = randomList;
            } else {
                int mid = randomList.size() / 2;
//                System.out.println("mid is " + mid);
                if (mid > 0) {
                    List<Integer> leftSort = randomList.subList(0, mid);
                    List<Integer> rightSort = randomList.subList(mid, randomList.size());
                    MultiSortJoinTask leftTask = new MultiSortJoinTask(leftSort);
                    MultiSortJoinTask rightTask = new MultiSortJoinTask(rightSort);
                    leftTask.fork();
                    rightTask.fork();
                    leftTask.join();
                    rightTask.join();
                    result = randomList;
                } else {
//                    System.out.println("mid is zero");
                }
            }
            return result;
        }
    }


    static class MutliSortProcess implements CacluCpuTime.Process<String> {

        private List<Integer> randomList = new ArrayList<>();

        public MutliSortProcess(List<Integer> randomList) {
            this.randomList.addAll(randomList);
//            System.out.println("array mutli size is "+randomList.size());
        }

        @Override
        public String process() {
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            MultiSortJoinTask sortJoinTask = new MultiSortJoinTask(randomList);
            Future<List<Integer>> listFuture = forkJoinPool.submit(sortJoinTask);
            try {
                randomList = listFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return "mutli thread sort  ok";
        }

        @Override
        public void printResult() {
            ForkJoinTaskTest.printResult(randomList);
        }
    }


    static class SingleSortProcess implements CacluCpuTime.Process<String> {
        private List<Integer> randomList = new ArrayList<>();

        public SingleSortProcess(List<Integer> tmp) {
            this.randomList.addAll(tmp);
        }

        @Override
        public String process() {
            randomList.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            return "singe thread ok";
        }

        @Override
        public void printResult() {
            ForkJoinTaskTest.printResult(randomList);
        }
    }


    private static void testSimpleForLoopAdd() {
        CacluCpuTime.cacluTime(new MutipleThreadTaskProcesser(), "mutilple core thread");
        CacluCpuTime.cacluTime(new SingleThreadTaskProcessor(), "single thread");
    }

    private static void testSortEfficient() {
        List<Integer> array = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < SORT_NUM_COUNT; i++) {
            array.add(random.nextInt(SORT_NUM_COUNT));
        }
        CacluCpuTime.cacluTime(new SingleSortProcess(array), "single thread sort");
        CacluCpuTime.cacluTime(new MutliSortProcess(array), "mutliple thread sort");
    }


    public static void main(String[] argv) {
//        testSimpleForLoopAdd();
        testSortEfficient();

    }
}
