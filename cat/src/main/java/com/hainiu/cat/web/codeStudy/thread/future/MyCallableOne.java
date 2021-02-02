package com.hainiu.cat.web.codeStudy.thread.future;

import java.util.concurrent.Callable;

/**
 * create by biji.zhao on 2020/12/19
 */
public class MyCallableOne implements Callable<String> {
    private int age;

    public MyCallableOne(int age) {
        this.age = age;
    }

    @Override
    public String call() throws Exception {
        int i = 0;

        while (i == 0) {
//            if (Thread.currentThread().isInterrupted()) { // 线程池执行任务被取消会中断
//                throw new InterruptedException();
//            }
            System.out.println("running");
        }
        return "返回值 年龄是：" + age;
    }
}
