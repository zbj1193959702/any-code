package com.hainiu.cat.web.codeStudy.thread.executor;

/**
 * create by biji.zhao on 2020/12/18
 */
public class ThreadX extends Thread {

    @Override
    public void run() {
        System.out.println(String.format("thread %s end", Thread.currentThread().getName()));
    }
}
