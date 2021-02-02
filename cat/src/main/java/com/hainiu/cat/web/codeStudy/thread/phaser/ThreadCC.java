package com.hainiu.cat.web.codeStudy.thread.phaser;


import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadCC extends Thread {
    private Phaser phaser;

    public ThreadCC(Phaser phaser) {
        this.phaser = phaser;
    }

    public ThreadCC() {
    }

    @Override
    public void run() {
        try {
            System.out.println(String.format("第一阶段 开始，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
//            phaser.awaitAdvance(0); // 参数 若和 getPhase()相等，则在屏障等待，否则继续向下执行 ，类似于观察者
//            phaser.awaitAdvanceInterruptibly(0);// 表示不可中断的

            Thread.sleep(3000);
            // 如果在指定时间内 getPhase未发生变化 就抛出异常，否则向下执行
            phaser.awaitAdvanceInterruptibly(0, 5, TimeUnit.SECONDS);

            System.out.println(String.format("第一阶段 结束，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
