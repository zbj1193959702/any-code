package com.hainiu.cat.web.codeStudy.thread.phaser;


import java.util.concurrent.Phaser;

/**
 * create by biji.zhao on 2020/12/16
 */
public class ThreadC extends Thread {
    private Phaser phaser;

    public ThreadC(Phaser phaser) {
        this.phaser = phaser;
    }

    public ThreadC() {
    }

    @Override
    public void run() {
        PrintTools.methodB();
    }
}
