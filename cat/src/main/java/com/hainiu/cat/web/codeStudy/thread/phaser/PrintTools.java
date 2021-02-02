package com.hainiu.cat.web.codeStudy.thread.phaser;

import java.util.concurrent.Phaser;

/**
 * create by biji.zhao on 2020/12/16
 */
public class PrintTools {
    public static Phaser phaser;


    public static void methodA() {
        System.out.println(String.format("%s %s methodA 1 begin ", Thread.currentThread().getName(), System.currentTimeMillis()));
        phaser.arriveAndAwaitAdvance();
        System.out.println(String.format("%s %s methodA 1 end ", Thread.currentThread().getName(), System.currentTimeMillis()));

        System.out.println(String.format("%s %s methodA 2 begin ", Thread.currentThread().getName(), System.currentTimeMillis()));
        phaser.arriveAndAwaitAdvance();
        System.out.println(String.format("%s %s methodA 2 end ", Thread.currentThread().getName(), System.currentTimeMillis()));
    }

    public static void methodB() {
        try {
            System.out.println(String.format("%s %s methodB 1 begin ", Thread.currentThread().getName(), System.currentTimeMillis()));
            Thread.sleep(5000);
            phaser.arriveAndAwaitAdvance();
            System.out.println(String.format("%s %s methodB 1 end ", Thread.currentThread().getName(), System.currentTimeMillis()));

            // 当前线程退出 并使 parties 减去1
            phaser.arriveAndDeregister();

            System.out.println(phaser.getArrivedParties());
//            System.out.println(String.format("%s %s methodB 2 begin ", Thread.currentThread().getName(), System.currentTimeMillis()));
//            Thread.sleep(5000);
//            phaser.arriveAndAwaitAdvance();
//            System.out.println(String.format("%s %s methodB 2 end ", Thread.currentThread().getName(), System.currentTimeMillis()));
        }catch (Exception e) {

        }
    }

}
