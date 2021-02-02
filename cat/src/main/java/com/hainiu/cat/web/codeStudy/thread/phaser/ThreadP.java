package com.hainiu.cat.web.codeStudy.thread.phaser;

import java.util.concurrent.Phaser;

/**
 * create by biji.zhao on 2020/12/17
 */
public class ThreadP extends Thread {

    private Phaser phaser;


    public ThreadP(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.println(" a begin");
        phaser.arriveAndAwaitAdvance();  // 调用一次相当于
        System.out.println("A end phase value="+ phaser.getPhase());

        System.out.println(" a begin");
        phaser.arriveAndAwaitAdvance();
        System.out.println("A end phase value="+ phaser.getPhase());

        System.out.println(" a begin");
        phaser.arriveAndAwaitAdvance();
        System.out.println("A end phase value="+ phaser.getPhase());

        System.out.println(" a begin");
        phaser.arriveAndAwaitAdvance();
        System.out.println("A end phase value="+ phaser.getPhase());

    }
}
