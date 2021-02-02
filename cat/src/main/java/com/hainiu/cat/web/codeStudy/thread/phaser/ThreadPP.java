package com.hainiu.cat.web.codeStudy.thread.phaser;


import java.util.concurrent.Phaser;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadPP extends Thread {
    private Phaser phaser;

    public ThreadPP(Phaser phaser) {
        this.phaser = phaser;
    }

    public ThreadPP() {
    }

    @Override
    public void run() {
        System.out.println(String.format("第一阶段 开始，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
        phaser.arriveAndAwaitAdvance();
        System.out.println(String.format("第一阶段 结束，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
    }
}
