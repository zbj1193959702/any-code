package com.hainiu.cat.web.codeStudy.thread.phaser;


import java.util.concurrent.Phaser;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadDD extends Thread {
    private Phaser phaser;

    public ThreadDD(Phaser phaser) {
        this.phaser = phaser;
    }

    public ThreadDD() {
    }

    @Override
    public void run() {
        try {
            System.out.println(String.format("第一阶段 开始，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
            Thread.sleep(5000);
            phaser.arriveAndAwaitAdvance();
            System.out.println(String.format("第一阶段 结束，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
