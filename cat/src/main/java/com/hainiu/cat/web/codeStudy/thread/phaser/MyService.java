package com.hainiu.cat.web.codeStudy.thread.phaser;

import java.util.concurrent.Phaser;

/**
 * create by biji.zhao on 2020/12/17
 */
public class MyService {
    private Phaser phaser;

    public MyService(Phaser phaser) {
        this.phaser = phaser;
    }

    public void testGetParties() {
        try {
            phaser.arriveAndAwaitAdvance();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void testPrintlnParties() {

        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000);
                System.out.println("\n");
                System.out.println(phaser.getArrivedParties());
                System.out.println(phaser.getUnarrivedParties());
            }
        }catch (Exception e){

        }

    }

    public void testMethod() {
        try {
            System.out.println(String.format("第一阶段 开始，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
            if (Thread.currentThread().getName().equals("B")) {
                Thread.sleep(5000);
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println(String.format("第一阶段 结束，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
            System.out.println("\n");

            System.out.println(String.format("第二阶段 开始，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
            if (Thread.currentThread().getName().equals("B")) {
                Thread.sleep(5000);
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println(String.format("第二阶段 结束，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));

            System.out.println("\n");
            System.out.println(String.format("第三阶段 开始，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));
            if (Thread.currentThread().getName().equals("B")) {
                Thread.sleep(5000);
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println(String.format("第三阶段 结束，线程：%s， 时间：%s", Thread.currentThread().getName(), System.currentTimeMillis()));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
