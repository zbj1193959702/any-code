package com.hainiu.cat.web.codeStudy.thread.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * create by biji.zhao on 2020/12/30
 */
public class MyRecursiveTaskA extends RecursiveTask<Integer> {
    @Override
    protected Integer compute() {
        System.out.println("MyRecursiveTaskA start");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("MyRecursiveTaskA end");
        return 100;
    }
}
