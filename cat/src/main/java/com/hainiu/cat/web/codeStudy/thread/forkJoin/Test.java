package com.hainiu.cat.web.codeStudy.thread.forkJoin;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * create by biji.zhao on 2020/12/30
 */
public class Test {

    private static Map<String, String> map = Maps.newHashMap();

    private static String getValue(String key) {
        String value = map.get(key);
        if (value == null) {
            return null;
        }
        if (value.equals("小花")) {
            return value;
        }
        return getValue(value);
    }

    public static void main(String[] args) {
//        testRun();
//        testForkRun();
//        joinAndGet();
//        testJoinGetException();
//        testSumbitMultpile();
//        testJoinString();
//        sumJoin();

    }

    public static void sumJoin() {
        SumRecursiveTask task = new SumRecursiveTask(1, 10);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(task);
        System.out.println(submit.join());
    }

    public static void testJoinString() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<String> submit = forkJoinPool.submit(new StringRecursiveTask(1, 20));

        System.out.println(submit.join());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testSumbitMultpile() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submitA = forkJoinPool.submit(new MyRecursiveTaskA());

        ForkJoinTask<Integer> submitB = forkJoinPool.submit(new MyRecursiveTaskB());

        // 并行任务之间是异步的，但是join()是同步阻塞的
        System.out.println("submitA: " + submitA.join());

        System.out.println("submitB: " + submitB.join());
    }

    public static void testJoinGetException() {
        try {
            ExceptionRecursiveTask exceptionRecursiveTask = new ExceptionRecursiveTask();
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            ForkJoinTask<Integer> submit = forkJoinPool.submit(exceptionRecursiveTask);

            // get是会主动抛出异常的 而join不会，但是都能够在主线程内捕获到这个异常
            System.out.println(submit.get());
        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ExceptionRecursiveTask exceptionRecursiveTask = new ExceptionRecursiveTask();
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            ForkJoinTask<Integer> submit = forkJoinPool.submit(exceptionRecursiveTask);

            System.out.println(submit.join());
        }catch (Exception e) {
            System.out.println("main Exception ================");
        }
    }

    public static void joinAndGet() throws InterruptedException, java.util.concurrent.ExecutionException {
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask();
        System.out.println(myRecursiveTask.hashCode());

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myRecursiveTask);

        System.out.println(submit.hashCode() + "---" + submit.join());
        // join get 都可以获取返回值
        System.out.println(submit.hashCode() + "---" + submit.get());

        Thread.sleep(3000);
    }

    public static void testForkRun() throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Void> submit = forkJoinPool.submit(new ForkRecursiveAction(1, 10));
        Thread.sleep(5000);
    }

    public static void testRun() throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Void> submit = forkJoinPool.submit(new MyRecursiveAction());
        Thread.sleep(5000);
    }
}
