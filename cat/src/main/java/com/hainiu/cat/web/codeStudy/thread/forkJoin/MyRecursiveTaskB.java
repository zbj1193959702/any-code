package com.hainiu.cat.web.codeStudy.thread.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * create by biji.zhao on 2020/12/30
 */
public class MyRecursiveTaskB extends RecursiveTask<Integer> {
    @Override
    protected Integer compute() {
        System.out.println("MyRecursiveTaskB start");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("MyRecursiveTaskB end");
        return 100;
    }
}
