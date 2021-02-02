package com.hainiu.cat.web.codeStudy.thread.phaser;

import java.util.concurrent.Phaser;

/**
 * create by biji.zhao on 2020/12/18
 */
public class MyServicePlus {
    private Phaser phaser;

    public MyServicePlus(Phaser phaser) {
        this.phaser = phaser;
    }

    public void testMethodA() {
        try {
            System.out.println(String.format("第一阶段 开始，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
            if ( Thread.currentThread().getName().equals("X")) {
                Thread.sleep(3000);
            }
            System.out.println(String.format("线程：%s, 到达数：%s", Thread.currentThread().getName(), phaser.getArrivedParties()));
            phaser.arriveAndAwaitAdvance();
            System.out.println(String.format("第一阶段 结束，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));

            System.out.println(String.format("第二阶段 开始，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
            Thread.sleep(3000);
            phaser.arriveAndAwaitAdvance();
            System.out.println(String.format("第二阶段 结束，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));

            System.out.println(String.format("第三阶段 开始，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
            Thread.sleep(3000);
            phaser.arriveAndAwaitAdvance();
            System.out.println(String.format("第三阶段 结束，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
        }catch (Exception e) {

        }
    }

    public void testMethodB() {
        try {
            System.out.println(String.format("testMethodB第一阶段 开始，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));

            phaser.arrive();
            System.out.println(String.format("testMethodB第一阶段 结束，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));

            System.out.println(String.format("testMethodB第二阶段 开始，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
            phaser.arrive();
            System.out.println(String.format("testMethodB第二阶段 结束，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));

            System.out.println(String.format("testMethodB第三阶段 开始，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
            phaser.arrive();
            System.out.println(String.format("testMethodB第三阶段 结束，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
        }catch (Exception e) {

        }

    }
}
