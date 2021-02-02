package com.hainiu.cat.web.codeStudy.thread.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * create by biji.zhao on 2020/12/30
 */
public class ExceptionRecursiveTask extends RecursiveTask<Integer> {
    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread().getName() + "执行compute方法");
        String nullString = null;
        nullString.toString();
        return 100;
    }
}
