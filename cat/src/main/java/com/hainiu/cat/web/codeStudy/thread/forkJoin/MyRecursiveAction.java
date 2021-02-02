package com.hainiu.cat.web.codeStudy.thread.forkJoin;

import java.util.concurrent.RecursiveAction;

/**
 * create by biji.zhao on 2020/12/30
 */
public class MyRecursiveAction extends RecursiveAction {
    @Override
    protected void compute() {
        System.out.println("执行。。。。");
    }
}
