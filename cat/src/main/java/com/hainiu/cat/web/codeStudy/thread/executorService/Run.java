package com.hainiu.cat.web.codeStudy.thread.executorService;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

/**
 * create by biji.zhao on 2020/12/22
 */
public class Run {

    public static void main(String[] args) {
//        testInvokeAny();
//        testInterrupted();
//        testInvokeAnyException();
//        testInvokeAllBase();

    }

    public static void testInvokeAllBase() {
        List<Callable<String>> callableList = Lists.newArrayList();
        callableList.add(new CallableAllA());
        callableList.add(new CallableAllB());

        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            // 阻塞等待所有任务执行后返回，并得到所有执行的结果

            // invokeAll 是可以处理具体的callable的异常的，invokeAny并不能捕获其中错误的任务
//            List<Future<String>> futures = executorService.invokeAll(callableList);

            // 如果全部任务在指定时间内没有执行完，将抛出异常
            List<Future<String>> futures = executorService.invokeAll(callableList, 1, TimeUnit.SECONDS);

            futures.forEach(future -> {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void testInvokeAnyException() {
        List<Callable<String>> callableList = Lists.newArrayList();
        callableList.add(new CallableA());
//        callableList.add(new CallableB());
//
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            // 线程都抛出异常的话，则返回最后一个异常信息，并返回最后一个的结果

            // 有了第二个参数的话，作用是在指定的时间内取得第一个执行完成任务的结果值

            // 即超时又出现异常
            String s = executorService.invokeAny(callableList, 1 , TimeUnit.SECONDS);
            System.out.println(s);
            System.out.println("main end");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }

    public static void testInterrupted() {
        List<Callable<String>> list = Lists.newArrayList();
        list.add(new MyCallableA());
        list.add(new MyCallableB3());

        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            // invokeAny 方法在取得第一个线程执行结果后将调用 isInterrupt 中断其他线程，但是
            // 其他线程执不执行取决于是否使用 Thread.currentThread().isInterrupted() 进行判断处理
            String o = executorService.invokeAny(list);
            System.out.println("====>>>>" + o);
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzz");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testInvokeAny() {
        List<Callable<String>> list = Lists.newArrayList();
        list.add(new MyCallableA());
        list.add(new MyCallableB2());

        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            // invokeAny 方法在取得第一个线程执行结果后将调用 isInterrupt 中断其他线程，但是
            // 其他线程执不执行取决于是否使用 Thread.currentThread().isInterrupted() 进行判断处理
            String o = executorService.invokeAny(list);
            System.out.println("====>>>>" + o);
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzz");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
